package com.challenge.csvimport.job.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public class JpaItemWriter<T> implements ItemWriter<T> {

    private final JpaRepository<T, Long> repository;

    public JpaItemWriter(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public void write(@NonNull Chunk<? extends T> chunk) {
        repository.saveAll(chunk);
    }
}
