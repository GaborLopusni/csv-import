package com.challenge.csvimport.controller.api;

import com.challenge.csvimport.entity.OutpayHeader;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OutpayHeaderController extends AbstractApiController<OutpayHeader> {
    @Autowired
    private JpaRepository<OutpayHeader, Long> repository;

    @Override
    @PostMapping
    public ResponseEntity<OutpayHeader> create() {
        return null;
    }

    @Override
    @GetMapping("/api/outpayHeaders/{id}")
    @ResponseBody
    public ResponseEntity<OutpayHeader> getById(@PathVariable("id") Long id) {

        final Optional<OutpayHeader> result = repository.findById(id);

        return result.map(outpayHeader -> new ResponseEntity<>(outpayHeader, HttpStatusCode.valueOf(200)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatusCode.valueOf(404)));
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<OutpayHeader>> list() {
        return null;
    }

    @Override
    public ResponseEntity<OutpayHeader> update() {
        return null;
    }

    @Override
    public ResponseEntity<OutpayHeader> delete() {
        return null;
    }
}
