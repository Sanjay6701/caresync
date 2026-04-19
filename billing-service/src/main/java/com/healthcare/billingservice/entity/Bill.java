package com.healthcare.billingservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private int totalAppointments;
    private double appointmentFee;
    private double medicineCost;
    private double totalAmount;

    @Column(length = 3000)
    private String dischargeSummary;

    private String status; // PENDING / PAID
}