package com.challenge.csvimport.controller;

import com.challenge.csvimport.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/job/policy")
@ResponseBody
@Slf4j
public class PolicyJobController extends JobController {

    @Autowired
    public PolicyJobController(ImportService policyImportService, String policyFileNamePattern) {
        super(policyImportService, policyFileNamePattern);
    }
}
