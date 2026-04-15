package com.healthcare.billingservice.service;

import com.healthcare.billingservice.entity.Bill;
import com.healthcare.billingservice.repository.BillRepository;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    private final BillRepository repository;

    public BillingService(BillRepository repository) {
        this.repository = repository;
    }

    public Bill generateBill(Bill bill) {

        double total = bill.getAppointmentFee() + bill.getMedicineCost();
        bill.setTotalAmount(total);
        bill.setStatus("PENDING");

        return repository.save(bill);
    }

    public Bill getBill(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }
}