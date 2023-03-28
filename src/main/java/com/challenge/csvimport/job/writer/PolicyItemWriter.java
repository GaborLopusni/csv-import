package com.challenge.csvimport.job.writer;

import com.challenge.csvimport.entity.Policy;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class PolicyItemWriter implements ItemWriter<Policy> {

    @Autowired
    private JpaRepository<Policy, Long> policyRepository;

    @Override
    public void write(Chunk<? extends Policy> chunk) {
        policyRepository.saveAll(chunk);
    }
}
