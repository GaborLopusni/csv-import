package com.challenge.csvimport.service;

import com.challenge.csvimport.entity.Policy;
import com.challenge.csvimport.job.reader.CustomFlatFileItemReader;
import com.challenge.csvimport.job.writer.JpaItemWriter;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
public class PolicyImportService extends AbstractImportService<Policy> {

    public PolicyImportService(JobRepository jobRepository, JobLauncher jobLauncher, PlatformTransactionManager platformTransactionManager, FlatFileItemReader<Policy> flatFileItemReader, ItemWriter<Policy> itemWriter) {
        super(jobRepository, jobLauncher, platformTransactionManager, flatFileItemReader, itemWriter);
    }
}
