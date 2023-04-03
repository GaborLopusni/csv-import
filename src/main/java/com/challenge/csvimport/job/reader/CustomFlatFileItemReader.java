package com.challenge.csvimport.job.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;

import java.nio.charset.Charset;

/**
 * Custom FlatFileItemReader for setting the properties of parent class
 * based on specified type
 */
public class CustomFlatFileItemReader<T> extends FlatFileItemReader<T> {

    public CustomFlatFileItemReader(LineMapper<T> lineMapper, Charset encoding) {
        super();
        this.setLineMapper(lineMapper);
        this.setEncoding(String.valueOf(encoding));
    }
}
