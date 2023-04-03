package com.challenge.csvimport.controller.exception;

import java.util.List;

/**
 * Exception that can be thrown when there is an invalid filename
 */
public class InvalidFileNamesException extends RuntimeException {
    private final List<String> fileNames;

    public InvalidFileNamesException(String message, List<String> fileNames) {
        super(message);
        this.fileNames = fileNames;
    }

    public List<String> getFileNames() {
        return fileNames;
    }
}
