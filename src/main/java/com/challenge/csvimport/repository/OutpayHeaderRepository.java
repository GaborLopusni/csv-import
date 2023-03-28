package com.challenge.csvimport.repository;

import com.challenge.csvimport.entity.OutpayHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutpayHeaderRepository extends JpaRepository<OutpayHeader, Long> {

}
