package com.challenge.csvimport.job.mapper;

import org.springframework.core.convert.converter.Converter;

/**
 * Converter for String to Double which can be registered into a ConversionService
 */
public interface StringToDoubleConverter extends Converter<String, Double> {
}
