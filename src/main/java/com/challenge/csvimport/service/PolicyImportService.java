package com.challenge.csvimport.service;

import com.challenge.csvimport.entity.Policy;
import com.challenge.csvimport.job.reader.CustomFlatFileItemReader;
import com.challenge.csvimport.job.writer.JpaItemWriter;
import org.springframework.stereotype.Service;

@Service
public class PolicyImportService extends AbstractImportService<Policy> {

    public PolicyImportService(CustomFlatFileItemReader<Policy> policyCustomFlatFileItemReader, JpaItemWriter<Policy> policyJpaItemWriter) {
        super(policyCustomFlatFileItemReader, policyJpaItemWriter);
    }
}
