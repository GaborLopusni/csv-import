package com.challenge.csvimport.repository;

import com.challenge.csvimport.entity.Policy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
