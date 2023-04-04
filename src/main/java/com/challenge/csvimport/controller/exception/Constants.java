package com.challenge.csvimport.controller.exception;

public class Constants {

    public static String PARSE_EXCEPTION_MESSAGE_TEMPLATE = "Import for resource: %s has failed, the file could not be parsed.";
    public static String DATA_ACCESS_EXCEPTION_MESSAGE_TEMPLATE = "Import has failed for resource: %s due to a database related error, please check the integrity of the data.";
    public static String GENERIC_EXCEPTION_MESSAGE_TEMPLATE = "Import has failed for resource: %s.";
}
