package com.healthcare.billingservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long appointmentId;
    private Long patientId;

    private double appointmentFee;
    private double medicineCost;
    private double totalAmount;

    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public double getAppointmentFee() {
		return appointmentFee;
	}

	public void setAppointmentFee(double appointmentFee) {
		this.appointmentFee = appointmentFee;
	}

	public double getMedicineCost() {
		return medicineCost;
	}

	public void setMedicineCost(double medicineCost) {
		this.medicineCost = medicineCost;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
    
}// PENDING / PAID

    // Getters