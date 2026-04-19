package com.healthcare.billingservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class PrescriptionDto {
    private Long appointmentId;
    private List<MedicineItem> medicines;
}