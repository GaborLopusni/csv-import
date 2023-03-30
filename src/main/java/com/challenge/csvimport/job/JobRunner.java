package com.challenge.csvimport.job;

import org.springframework.core.io.Resource;


public interface JobRunner {

    void run(Resource resource) throws Exception;
}
