package com.challenge.csvimport.controller;

import com.challenge.csvimport.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/job/import")
@ResponseBody
@Slf4j
public class JobExecutionController {

    @Autowired
    private ImportService policyImportService;

    @PostMapping
    public ResponseEntity<List<String>> executeImport(@RequestParam("files") MultipartFile[] files) {
        Assert.state(
                Arrays.stream(files).noneMatch(multipartFile -> multipartFile.getName().isBlank()),
                "The file cannot be empty in the request body.");

        Arrays.stream(files).forEach(multipartFile -> log.info("Executing job for: FileName: {}", multipartFile.getOriginalFilename()));
        List<String> fileNames = Arrays.stream(files).map(MultipartFile::getOriginalFilename).toList();

        Arrays.stream(files).map(MultipartFile::getResource).forEach(resource -> {
                    try {
                        policyImportService.executeImport(resource);
                    } catch (Exception e) {
                        log.error("Import for resource: {} has failed: {}", resource.getFilename(), e.getMessage());
                        throw new RuntimeException("Import has failed");
                    }
                }

        );

        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }
}
