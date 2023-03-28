package com.challenge.csvimport.job;

import com.challenge.csvimport.entity.Policy;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class PolicyImportJobRunner implements JobRunner {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private FlatFileItemReader<Policy> policyFlatFileItemReader;

    @Autowired
    private ItemWriter<Policy> policyItemWriter;

    @Override
    public void run(Resource resource) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobRestartException, JobParametersInvalidException {
        policyFlatFileItemReader.setResource(resource);

        var step = new StepBuilder("policy-step", jobRepository)
                .<Policy, Policy>chunk(10, transactionManager)
                .reader(policyFlatFileItemReader)
                .writer(policyItemWriter)
                .build();

        var job = new JobBuilder("Policy", jobRepository)
                .start(step)
                .build();

        jobLauncher.run(job, new JobParameters());
    }
}
