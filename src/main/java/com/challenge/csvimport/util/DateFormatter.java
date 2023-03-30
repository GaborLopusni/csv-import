package com.challenge.csvimport.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static Date formatDate(String date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
