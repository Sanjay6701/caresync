package com.healthcare.billingservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthcare.billingservice.clients.*;
import com.healthcare.billingservice.dto.*;
import com.healthcare.billingservice.entity.Bill;
import com.healthcare.billingservice.repository.BillRepository;

@Service
public class BillingService {

    private final BillRepository repository;
    private final AppointmentFeignClient appointmentClient;
    private final PrescriptionFeignClient prescriptionClient;
    private final PharmaFeignClient pharmaClient;

    public BillingService(
            BillRepository repository,
            AppointmentFeignClient appointmentClient,
            PrescriptionFeignClient prescriptionClient,
            PharmaFeignClient pharmaClient) {

        this.repository = repository;
        this.appointmentClient = appointmentClient;
        this.prescriptionClient = prescriptionClient;
        this.pharmaClient = pharmaClient;
    }

    // ✅ GENERATE BILL
    public Bill generateBill(Long patientId) {

        if (patientId == null)
            throw new IllegalArgumentException("Patient ID is required");

        // ✅ Get today's appointments
        List<AppointmentDto> appointments =
                appointmentClient.getTodayAppointments(patientId).getData();

        if (appointments == null || appointments.isEmpty())
            throw new RuntimeException("No appointments found for today");

        double appointmentTotal = 0;
        double medicineTotal = 0;
        StringBuilder summary = new StringBuilder();

        for (AppointmentDto appt : appointments) {

            appointmentTotal += appt.getPrice();

            PrescriptionDto prescription =
                    prescriptionClient.getPrescriptionByAppointmentId(appt.getId()).getData();

            if (prescription != null) {
                for (MedicineItem med : prescription.getMedicines()) {

                    double price =
                            pharmaClient.getMedicinePrice(med.getName())
                                    .getData().getPricePerUnit();

                    medicineTotal += price * med.getQuantity();

                    summary.append(
                            "Medicine: " + med.getName()
                            + ", Qty: " + med.getQuantity()
                            + ", Unit Price: " + price + "\n"
                    );
                }
            }
        }

        Bill bill = new Bill();
        bill.setPatientId(patientId);
        bill.setTotalAppointments(appointments.size());
        bill.setAppointmentFee(appointmentTotal);
        bill.setMedicineCost(medicineTotal);
        bill.setTotalAmount(appointmentTotal + medicineTotal);
        bill.setStatus("PENDING");
        bill.setDischargeSummary(summary.toString());

        return repository.save(bill);
    }
}