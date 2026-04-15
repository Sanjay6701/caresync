package com.healthcare.billingservice.repository;

import com.healthcare.billingservice.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {}