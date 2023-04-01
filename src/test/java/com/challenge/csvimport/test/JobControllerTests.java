package com.challenge.csvimport.test;

import com.challenge.csvimport.controller.JobController;
import com.challenge.csvimport.controller.PolicyJobController;
import com.challenge.csvimport.controller.exception.ExceptionHandlingControllerAdvice;
import com.challenge.csvimport.controller.exception.InvalidFileNamesException;
import com.challenge.csvimport.service.ImportService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JobControllerTests {

    private JobController policyJobController;
    private static ImportService importService;

    @BeforeAll
    public static void setUp() {
        importService = mock(ImportService.class);
    }

    @ParameterizedTest
    @ArgumentsSource(JobControllerTestArgumentsProvider.class)
    public void policyJobControllerFailureTest(String filePattern, MultipartFile[] multipartFiles, Exception expectedException, String expectedResult) throws Exception {

        policyJobController = new PolicyJobController(importService, filePattern);
        doThrow(expectedException).when(importService).executeImport(any());

        var exception = assertThrows(
                RuntimeException.class,
                () -> policyJobController.executeJob(multipartFiles));

        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    public void policyJobControllerSuccessfulTest() throws Exception {
        policyJobController = new PolicyJobController(importService, "CUSTCOMP[0-9]{2}\\.(TXT|txt)");
        doNothing().when(importService).executeImport(any());
        var multipartFiles = new MockMultipartFile[]{new MockMultipartFile("CUSTCOMP01.txt", "CUSTCOMP01.txt", null, (byte[]) null)};
        var response = policyJobController.executeJob(multipartFiles);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("CUSTCOMP01.txt", response.getBody().get(0));
    }

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
