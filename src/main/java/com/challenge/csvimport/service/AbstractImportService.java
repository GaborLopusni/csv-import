package com.challenge.csvimport.service;

import com.challenge.csvimport.controller.exception.CsvImportRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * Abstract import service which builds the Spring Batch
 * job and executes it
 */
@Slf4j
public class AbstractImportService<T> implements ImportService {

    private final JobRepository jobRepository;

    private final JobLauncher jobLauncher;

    private final PlatformTransactionManager platformTransactionManager;

    private final FlatFileItemReader<T> flatFileItemReader;

    private final ItemWriter<T> itemWriter;

    @Autowired
    public AbstractImportService(JobRepository jobRepository, JobLauncher jobLauncher, PlatformTransactionManager platformTransactionManager, FlatFileItemReader<T> flatFileItemReader, ItemWriter<T> itemWriter) {
        this.jobRepository = jobRepository;
        this.jobLauncher = jobLauncher;
        this.platformTransactionManager = platformTransactionManager;
        this.flatFileItemReader = flatFileItemReader;
        this.itemWriter = itemWriter;
    }

    /**
     * Builds and executes import job for a resource
     *
     * @param resource input resource to process
     */
    @Override
    public void executeImport(Resource resource) {
        flatFileItemReader.setResource(resource);
        JobExecution jobExecution;

        var fileName = resource.getFilename();
        assert fileName != null;

        var step = new StepBuilder("import-step", jobRepository)
                .<T, T>chunk(10, platformTransactionManager)
                .reader(flatFileItemReader)
                .writer(itemWriter)
                .allowStartIfComplete(true)
                .build();

        var job = new JobBuilder(fileName, jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();

        try {
            jobExecution = jobLauncher.run(job, new JobParameters());
        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException |
                 JobParametersInvalidException | JobRestartException exception) {
            throw new CsvImportRuntimeException(exception.getMessage());
        }

        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            throw new CsvImportRuntimeException(jobExecution.getAllFailureExceptions().get(0).getMessage());
        }
    }
}

