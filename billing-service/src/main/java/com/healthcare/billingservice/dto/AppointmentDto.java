package com.healthcare.billingservice.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AppointmentDto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime bookingDateTime;
    private double price;
}