package com.challenge.csvimport.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
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
     * Builds and executes import for a resource
     * @param resource input resource to process
     */
    @Override
    public void executeImport(Resource resource) throws Exception {
        flatFileItemReader.setResource(resource);
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

        var jobExecution = jobLauncher.run(job, new JobParameters());

        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            throw (Exception) jobExecution.getAllFailureExceptions().get(0);
        }
    }
}

