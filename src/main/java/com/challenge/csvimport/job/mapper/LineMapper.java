package com.challenge.csvimport.job.mapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;

public class LineMapper<T> extends DefaultLineMapper<T> {

    public LineMapper(Class<T> type, LineTokenizer lineTokenizer) {
        super();
        this.setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
            {
                setTargetType(type);
            }
        });
        this.setLineTokenizer(lineTokenizer);
    }
}
