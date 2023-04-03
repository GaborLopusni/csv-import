package com.challenge.csvimport.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


@Component
public class DateTimeFormatter {
    /**
     * Formats date using the specified pattern
     * @param date date to format
     * @param pattern the patter
     * @return formatted LocalDate instance or null
     */
    public LocalDate formatDate(String date, String pattern) {
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(pattern);

        try {
            return LocalDate.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
