package com.challenge.csvimport.service;

import com.challenge.csvimport.entity.Redemption;
import com.challenge.csvimport.job.reader.CustomFlatFileItemReader;
import com.challenge.csvimport.job.writer.JpaItemWriter;
import org.springframework.stereotype.Service;

@Service
public class RedemptionImportService extends AbstractImportService<Redemption> {

    public RedemptionImportService(CustomFlatFileItemReader<Redemption> redemptionCustomFlatFileItemReader, JpaItemWriter<Redemption> redemptionJpaItemWriter) {
        super(redemptionCustomFlatFileItemReader, redemptionJpaItemWriter);
    }
}
