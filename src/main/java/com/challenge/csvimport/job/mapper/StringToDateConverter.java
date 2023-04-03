package com.challenge.csvimport.job.mapper;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * Converter for String to LocalDate which can be registered into a ConversionService
 */
public interface StringToDateConverter extends Converter<String, LocalDate> {
}
