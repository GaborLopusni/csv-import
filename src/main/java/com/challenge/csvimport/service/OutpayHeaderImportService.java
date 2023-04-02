package com.challenge.csvimport.service;

import com.challenge.csvimport.entity.OutpayHeader;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

@Service
public class OutpayHeaderImportService extends AbstractImportService<OutpayHeader> {

    public OutpayHeaderImportService(JobRepository jobRepository, JobLauncher jobLauncher, PlatformTransactionManager platformTransactionManager, FlatFileItemReader<OutpayHeader> flatFileItemReader, ItemWriter<OutpayHeader> itemWriter) {
        super(jobRepository, jobLauncher, platformTransactionManager, flatFileItemReader, itemWriter);
    }
}
