package com.challenge.csvimport.controller;

import com.challenge.csvimport.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/job/outpayHeader")
@ResponseBody
@Slf4j
public class OutpayHeaderJobController extends JobController {

    @Autowired
    public OutpayHeaderJobController(ImportService outpayHeaderImportService) {
        super(outpayHeaderImportService);
    }
}
