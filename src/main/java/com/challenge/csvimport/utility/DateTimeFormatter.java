package com.challenge.csvimport.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class DateTimeFormatter {
    public LocalDate formatDate(String date, String pattern) {
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(pattern);

        try {
            return LocalDate.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
