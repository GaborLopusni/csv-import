package com.challenge.csvimport.test;

import com.challenge.csvimport.controller.JobController;
import com.challenge.csvimport.controller.PolicyJobController;
import com.challenge.csvimport.service.ImportService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JobControllerTest {

    private static ImportService importService;

    private JobController jobController;

    @BeforeAll
    public static void setUp() {
        importService = mock(ImportService.class);
    }

    @ParameterizedTest
    @ArgumentsSource(JobControllerTestArgumentsProvider.class)
    public void jobControllerFailureTest(String filePattern, MultipartFile[] multipartFiles, Exception expectedException, String expectedResult) throws Exception {

        jobController = new PolicyJobController(importService, filePattern);
        doThrow(expectedException).when(importService).executeImport(any());

        var exception = assertThrows(
                RuntimeException.class,
                () -> jobController.executeJob(multipartFiles));

        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    public void jobControllerSuccessfulTest() throws Exception {
        jobController = new PolicyJobController(importService, "CUSTCOMP[0-9]{2}\\.(TXT|txt)");
        doNothing().when(importService).executeImport(any());
        var multipartFiles = new MockMultipartFile[]{new MockMultipartFile("CUSTCOMP01.txt", "CUSTCOMP01.txt", null, (byte[]) null)};
        var response = jobController.executeJob(multipartFiles);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("CUSTCOMP01.txt", response.getBody().get(0));
    }
}
