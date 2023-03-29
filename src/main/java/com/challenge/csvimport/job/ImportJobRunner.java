package com.challenge.csvimport.job;

import com.challenge.csvimport.job.reader.CustomFlatFileItemReader;
import com.challenge.csvimport.job.writer.JpaItemWriter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;


public class ImportJobRunner<T> implements JobRunner {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private final CustomFlatFileItemReader<T> flatFileItemReader;

    private final JpaItemWriter<T> jpaItemWriter;

    public ImportJobRunner(CustomFlatFileItemReader<T> flatFileItemReader, JpaItemWriter<T> jpaItemWriter) {
        this.flatFileItemReader = flatFileItemReader;
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void run(Resource resource) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobRestartException, JobParametersInvalidException {
        flatFileItemReader.setResource(resource);
        var fileName = resource.getFilename() != null ? resource.getFilename() : "unknown";

        var step = new StepBuilder("import-step", jobRepository)
                .<T, T>chunk(10, transactionManager)
                .reader(flatFileItemReader)
                .writer(jpaItemWriter)
                .allowStartIfComplete(true)
                .build();

        var job = new JobBuilder(fileName, jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();

        jobLauncher.run(job, new JobParameters());
    }
}