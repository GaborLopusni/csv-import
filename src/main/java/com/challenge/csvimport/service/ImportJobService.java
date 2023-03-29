package com.challenge.csvimport.service;

import com.challenge.csvimport.job.JobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

@Slf4j
public class ImportJobService implements ImportService {

    private final JobRunner importJobRunner;

    public ImportJobService(JobRunner importJobRunner) {
        this.importJobRunner = importJobRunner;
    }

    @Override
    public void executeImport(Resource resource) throws Exception {
        importJobRunner.run(resource);
    }
}
