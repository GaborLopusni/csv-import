package com.challenge.csvimport.test;

import com.challenge.csvimport.controller.exception.ExceptionHandlingControllerAdvice;
import com.challenge.csvimport.controller.exception.InvalidFileNamesException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExceptionHandlingControllerAdviceTest {

    @Test
    public void controllerAdviceTest() {
        var controllerAdvice = new ExceptionHandlingControllerAdvice();
        var exception = new RuntimeException("some message");
        var response = controllerAdvice.handleRuntimeException(exception);

        assertNotNull(response);
        assertNotNull(response.get("error"));
        assertEquals("some message", response.get("error"));

        var failedFiles = Collections.singletonList("test.txt");
        exception = new InvalidFileNamesException("Filenames are invalid", failedFiles);
        response = controllerAdvice.handleRuntimeException(exception);

        assertNotNull(response);
        assertNotNull(response.get("error"));
        assertNotNull(response.get("detail"));

        assertEquals("Filenames are invalid", response.get("error"));
        assertEquals(failedFiles, response.get("detail"));
    }
}
