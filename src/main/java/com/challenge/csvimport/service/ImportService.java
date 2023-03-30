package com.challenge.csvimport.service;

import org.springframework.core.io.Resource;

public interface ImportService {
    void executeImport(Resource resource) throws Exception;
}
