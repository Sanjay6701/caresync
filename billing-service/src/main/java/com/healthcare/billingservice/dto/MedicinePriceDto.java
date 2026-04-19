package com.healthcare.billingservice.dto;

import lombok.Data;

@Data
public class MedicinePriceDto {
    private String name;
    private double pricePerUnit;
}