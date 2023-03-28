package com.challenge.csvimport.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/job/import")
@ResponseBody
@Slf4j
public class JobExecutionController {

    @PostMapping
    public ResponseEntity<List<String>> executeImport(@RequestParam("files") MultipartFile[] files) {
        Assert.state(
                Arrays.stream(files).noneMatch(multipartFile -> Objects.equals(multipartFile.getOriginalFilename(), "")),
                "The file cannot be empty in the request body.");

        Arrays.stream(files).forEach(multipartFile -> log.info("Executing job for: FileName: {}", multipartFile.getOriginalFilename()));
        List<String> fileNames = Arrays.stream(files).map(MultipartFile::getOriginalFilename).toList();

        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }
}
