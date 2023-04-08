package com.challenge.csvimport.controller;

import com.challenge.csvimport.controller.exception.CsvImportRuntimeException;
import com.challenge.csvimport.controller.exception.InvalidFileNamesException;
import com.challenge.csvimport.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.challenge.csvimport.controller.exception.Constants.*;

/**
 * Abstract controller class implementing the common job execution steps
 * and handle exceptions
 */
@Slf4j
public abstract class JobController {

    private final ImportService importService;

    private final String filePattern;

    protected JobController(ImportService importService, String filePattern) {
        this.importService = importService;
        this.filePattern = filePattern;
    }

    /**
     * Handles the POST requests, and triggers
     * the import service execution with the input files
     *
     * @param files file inputs
     * @return the response with the list of successfully imported files
     */
    @PostMapping
    public ResponseEntity<List<String>> executeJob(@RequestParam("files") MultipartFile[] files) {
        var fileNames = Arrays.stream(files).map(multipartFile -> multipartFile.getOriginalFilename() != null ? multipartFile.getOriginalFilename() : "").toList();

        Assert.state(
                fileNames.stream().noneMatch(fileName -> fileName == null || fileName.isEmpty()),
                "The file cannot be empty in the request body.");

        validateFileNames(fileNames);
        log.info("Executing job for: FileNames: {}", fileNames);

        Arrays.stream(files).map(MultipartFile::getResource).forEach(resource -> {
                    try {
                        importService.executeImport(resource);
                    } catch (FlatFileParseException flatFileParseException) {
                        log.error("Import for resource: {} has failed, file could not be parsed.", resource.getFilename());
                        throw new CsvImportRuntimeException(String.format(PARSE_EXCEPTION_MESSAGE_TEMPLATE, resource.getFilename()));
                    } catch (DataAccessException e) {
                        log.error("Import for resource: {} has failed: {}.", resource.getFilename(), e.getMessage());
                        throw new CsvImportRuntimeException(String.format(DATA_ACCESS_EXCEPTION_MESSAGE_TEMPLATE, resource.getFilename()));
                    } catch (Exception e) {
                        log.error("Import for resource: {} has failed: {}.", resource.getFilename(), e.getMessage());
                        throw new CsvImportRuntimeException(String.format(GENERIC_EXCEPTION_MESSAGE_TEMPLATE, resource.getFilename()));
                    }
                }
        );

        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Validates file names
     *
     * @param fileNames file inputs
     * @throws InvalidFileNamesException when invalid filename is found
     */
    private void validateFileNames(List<String> fileNames) {
        var failedNames = new ArrayList<>(fileNames.stream().filter(s -> !validate(s)).toList());

        if (!failedNames.isEmpty()) {
            throw new InvalidFileNamesException("Some of the filenames are invalid.", failedNames);
        }
    }

    /**
     * Validates file name
     *
     * @param fileName file input
     * @return returns false if the input doesn't match the regexp
     */
    private boolean validate(String fileName) {
        return fileName.matches(filePattern);
    }
}
