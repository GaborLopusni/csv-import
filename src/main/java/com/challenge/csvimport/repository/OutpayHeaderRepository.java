package com.challenge.csvimport.repository;

import com.challenge.csvimport.entity.OutpayHeader;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OutpayHeaderRepository extends JpaRepository<OutpayHeader, Long> {

}
