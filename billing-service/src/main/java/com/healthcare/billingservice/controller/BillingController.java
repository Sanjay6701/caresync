package com.healthcare.billingservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.healthcare.billingservice.dto.ApiResponseDto;
import com.healthcare.billingservice.entity.Bill;
import com.healthcare.billingservice.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService service;

    public BillingController(BillingService service) {
        this.service = service;
    }

    @PostMapping("/generate/{patientId}")
    public ApiResponseDto<Bill> generateBill(
            @PathVariable Long patientId) {

        return new ApiResponseDto<>(
                true,
                "Bill generated successfully",
                service.generateBill(patientId),
                HttpStatus.CREATED.value()
        );
    }
}