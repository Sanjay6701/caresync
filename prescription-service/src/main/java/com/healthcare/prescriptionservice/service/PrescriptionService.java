package com.healthcare.prescriptionservice.service;

import com.healthcare.prescriptionservice.entity.Prescription;
import com.healthcare.prescriptionservice.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository repository;

    public PrescriptionService(PrescriptionRepository repository) {
        this.repository = repository;
    }

    public Prescription create(Prescription p) {
        p.setStatus("CREATED");
        return repository.save(p);
    }

    public Prescription get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    public List<Prescription> byPatient(Long id) {
        return repository.findByPatientId(id);
    }

    public List<Prescription> byDoctor(Long id) {
        return repository.findByDoctorId(id);
    }
}