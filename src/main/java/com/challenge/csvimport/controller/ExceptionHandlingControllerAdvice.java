package com.challenge.csvimport.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler
    public ProblemDetail handleRuntimeException(RuntimeException e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
        problemDetail.setDetail(e.getMessage());
        log.info(e.getMessage());
        return problemDetail;
    }
}