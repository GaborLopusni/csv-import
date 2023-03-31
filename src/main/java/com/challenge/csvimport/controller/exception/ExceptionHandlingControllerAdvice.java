package com.challenge.csvimport.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        var errors = new HashMap<String, Object>();
        errors.put("error", "Something went wrong during import.");
        errors.put("detail", e.getMessage());

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFileNamesException.class)
    public Map<String, Object> handleInvalidFileNamesException(InvalidFileNamesException e) {
        var errors = new HashMap<String, Object>();
        errors.put("detail", e.getFileNames());
        errors.put("message", e.getMessage());

        return errors;
    }
}