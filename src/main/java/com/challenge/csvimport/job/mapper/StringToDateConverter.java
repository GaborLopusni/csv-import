package com.challenge.csvimport.job.mapper;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public interface StringToDateConverter extends Converter<String, LocalDate> {
}
