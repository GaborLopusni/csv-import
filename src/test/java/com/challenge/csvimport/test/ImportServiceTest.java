package com.challenge.csvimport.test;

import com.challenge.csvimport.entity.Redemption;
import com.challenge.csvimport.job.writer.JpaItemWriter;
import com.challenge.csvimport.repository.RedemptionRepository;
import com.challenge.csvimport.service.ImportService;
import com.challenge.csvimport.service.RedemptionImportService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ImportServiceTest {

    private static ImportService importService;

    private static Resource resource;

    private static JobExecution jobExecution;

    private static JobLauncher jobLauncher;

    private static List<Redemption> getMockedEntities() {
        return Collections.singletonList(new Redemption());
    }

    @BeforeAll
    public static void init() throws IOException {
        //Create mocks
        jobExecution = Mockito.mock(JobExecution.class);
        JobRepository jobRepository = Mockito.mock(JobRepository.class);
        PlatformTransactionManager transactionManager = Mockito.mock(PlatformTransactionManager.class);
        jobLauncher = Mockito.mock(JobLauncher.class);
        JpaRepository<Redemption, Long> jpaRepository = Mockito.mock(RedemptionRepository.class);
        FlatFileItemReader<Redemption> flatFileItemReader = Mockito.mock(FlatFileItemReader.class);
        JpaItemWriter<Redemption> itemWriter = new JpaItemWriter<>(jpaRepository);
        Mockito.when(jpaRepository.saveAll(getMockedEntities())).thenReturn(getMockedEntities());
        importService = new RedemptionImportService(jobRepository, jobLauncher, transactionManager, flatFileItemReader, itemWriter);

        //Create stubbed temp file
        var tempFile = File.createTempFile("testfile", ".txt");
        Files.writeString(tempFile.toPath(), "test content\n");
        resource = new FileSystemResource(tempFile);
        tempFile.deleteOnExit();
    }

    private void mockFailedExecution() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Mockito.when(jobExecution.getStatus()).thenReturn(BatchStatus.FAILED);
        Mockito.when(jobExecution.getAllFailureExceptions()).thenReturn(Collections.singletonList(new Exception("test exception")));
        mockJobLauncher();
    }

    private void mockSuccessfulExecution() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Mockito.when(jobExecution.getStatus()).thenReturn(BatchStatus.COMPLETED);
        mockJobLauncher();
    }

    private void mockJobLauncher() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Mockito.when(jobLauncher.run(any(), any())).thenReturn(jobExecution);
    }

    @Test
    void whenExecutionHasFailed_thenExceptionShouldBeThrown() throws Exception {
        mockFailedExecution();
        Exception exception = assertThrows(
                Exception.class,
                () -> importService.executeImport(resource));

        assertEquals("test exception", exception.getMessage());
    }

    @Test
    void whenExecutionIsCompleted_thenExceptionShouldNotBeThrown() throws Exception {
        mockSuccessfulExecution();
        assertDoesNotThrow(() -> importService.executeImport(resource));
    }
}
