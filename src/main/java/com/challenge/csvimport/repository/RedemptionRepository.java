package com.challenge.csvimport.repository;

import com.challenge.csvimport.entity.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedemptionRepository extends JpaRepository<Redemption, Long> {

}