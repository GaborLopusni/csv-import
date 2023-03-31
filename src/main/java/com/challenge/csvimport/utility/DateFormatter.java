package com.challenge.csvimport.utility;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatter {
    public Date formatDate(String date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
