package com.challenge.csvimport.service;

import com.challenge.csvimport.entity.Policy;
import com.challenge.csvimport.job.JobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PolicyImportService implements ImportService {

    @Autowired
    private LineMapper<Policy> policyLineMapper;

    @Autowired
    private JobRunner policyImportJobRunner;

    @Override
    public void executeImport(Resource resource) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobRestartException, JobParametersInvalidException {
        policyImportJobRunner.run(resource);
    }
}
