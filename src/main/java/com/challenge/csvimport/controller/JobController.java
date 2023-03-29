package com.challenge.csvimport.controller;

import com.challenge.csvimport.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;


@Slf4j
public abstract class JobController {

    private final ImportService importService;

    public JobController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping
    public ResponseEntity<List<String>> executeJob(@RequestParam("files") MultipartFile[] files) {
        Assert.state(
                Arrays.stream(files).noneMatch(multipartFile -> multipartFile.getName().isBlank()),
                "The file cannot be empty in the request body.");

        Arrays.stream(files).forEach(multipartFile -> log.info("Executing job for: FileName: {}", multipartFile.getOriginalFilename()));
        List<String> fileNames = Arrays.stream(files).map(MultipartFile::getOriginalFilename).toList();

        Arrays.stream(files).map(MultipartFile::getResource).forEach(resource -> {
                    try {
                        importService.executeImport(resource);
                    } catch (FlatFileParseException flatFileParseException) {
                        log.error("Import for resource: {} has failed, file could not be parsed.", resource.getFilename(), flatFileParseException);
                        throw new RuntimeException("Import for resource: {} has failed, file could not be parsed.");
                    } catch (Exception e) {
                        log.error("Import for resource: {} has failed: {}.", resource.getFilename(), e.getMessage());
                        throw new RuntimeException("Import has failed");
                    }
                }
        );

        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }
}
