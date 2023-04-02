package com.challenge.csvimport.service;

import com.challenge.csvimport.entity.Redemption;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
public class RedemptionImportService extends AbstractImportService<Redemption> {

    public RedemptionImportService(JobRepository jobRepository, JobLauncher jobLauncher, PlatformTransactionManager platformTransactionManager, FlatFileItemReader<Redemption> flatFileItemReader, ItemWriter<Redemption> jpaItemWriter) {
        super(jobRepository, jobLauncher, platformTransactionManager, flatFileItemReader, jpaItemWriter);
    }
}
