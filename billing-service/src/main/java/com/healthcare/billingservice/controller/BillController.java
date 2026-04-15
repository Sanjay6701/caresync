package com.healthcare.billingservice.controller;

import com.healthcare.billingservice.entity.Bill;
import com.healthcare.billingservice.service.BillingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillingService service;

    public BillController(BillingService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public Bill generate(@RequestBody Bill bill) {
        return service.generateBill(bill);
    }

    @GetMapping("/{id}")
    public Bill get(@PathVariable Long id) {
        return service.getBill(id);
    }
}