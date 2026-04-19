package com.healthcare.billingservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.healthcare.billingservice.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByPatientIdAndStatus(Long patientId, String status);
}