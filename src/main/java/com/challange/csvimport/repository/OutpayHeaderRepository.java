package com.challange.csvimport.repository;

import com.challange.csvimport.entity.OutpayHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutpayHeaderRepository extends JpaRepository<OutpayHeader, Long> {

}
