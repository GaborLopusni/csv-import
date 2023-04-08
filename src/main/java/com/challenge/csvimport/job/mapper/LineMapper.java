package com.challenge.csvimport.job.mapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.convert.ConversionService;

/**
 * Custom LineMapper to set properties on parent class
 * including Class, LineTokenizer and ConversionService
 */
public class LineMapper<T> extends DefaultLineMapper<T> {

    public LineMapper(Class<T> type, LineTokenizer lineTokenizer, ConversionService conversionService) {
        super();
        var beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<T>();
        beanWrapperFieldSetMapper.setTargetType(type);
        beanWrapperFieldSetMapper.setConversionService(conversionService);
        this.setFieldSetMapper(beanWrapperFieldSetMapper);
        this.setLineTokenizer(lineTokenizer);
    }
}
