package com.challenge.csvimport.service;

import com.challenge.csvimport.entity.OutpayHeader;
import com.challenge.csvimport.job.reader.CustomFlatFileItemReader;
import com.challenge.csvimport.job.writer.JpaItemWriter;
import org.springframework.stereotype.Service;

@Service
public class OutpayHeaderImportService extends AbstractImportService<OutpayHeader> {

    public OutpayHeaderImportService(CustomFlatFileItemReader<OutpayHeader> outpayHeaderCustomFlatFileItemReader, JpaItemWriter<OutpayHeader> outpayHeaderJpaItemWriter) {
        super(outpayHeaderCustomFlatFileItemReader, outpayHeaderJpaItemWriter);
    }
}
