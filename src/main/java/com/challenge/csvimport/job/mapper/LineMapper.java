package com.challenge.csvimport.job.mapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.convert.ConversionService;

public class LineMapper<T> extends DefaultLineMapper<T> {

    public LineMapper(Class<T> type, LineTokenizer lineTokenizer, ConversionService conversionService) {
        super();
        this.setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
            {
                setTargetType(type);
                setConversionService(conversionService);
            }
        });
        this.setLineTokenizer(lineTokenizer);
    }
}
