package com.challenge.csvimport.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * ControllerAdvice to handle the controller level exceptions
 * and return the regarding response
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Handles controller level exceptions
     *
     * @param e exception that should be handled
     * @return response with status code 400 and
     * a Map based on the exception message and type
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        var errors = new HashMap<String, Object>();
        errors.put("error", e.getMessage());

        if (e instanceof InvalidFileNamesException) {
            errors.put("detail", ((InvalidFileNamesException) e).getFileNames());
        }

        return errors;
    }
}