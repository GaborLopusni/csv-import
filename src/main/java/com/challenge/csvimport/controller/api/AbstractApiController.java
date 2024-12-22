package com.challenge.csvimport.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractApiController<T> {
    public abstract ResponseEntity<T> create();
    public abstract ResponseEntity<T> getById(Long id);
    public abstract ResponseEntity<List<T>> list();
    public abstract ResponseEntity<T> update();
    public abstract ResponseEntity<T> delete();
}
