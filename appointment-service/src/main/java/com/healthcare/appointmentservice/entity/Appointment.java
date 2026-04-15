package com.healthcare.appointmentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "doctorId", "timeSlot", "date" })
})

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String timeSlot;
    private String status;
    private String date;
}