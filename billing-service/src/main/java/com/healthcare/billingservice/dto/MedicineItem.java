package com.healthcare.billingservice.dto;

import lombok.Data;

@Data
public class MedicineItem {
    private String name;
    private int quantity;
}