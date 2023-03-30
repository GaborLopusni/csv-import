package com.challenge.csvimport.job.mapper;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public interface StringToDateConverter extends Converter<String, Date> {
}
