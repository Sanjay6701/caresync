package com.healthcare.prescriptionservice.controller;

import com.healthcare.prescriptionservice.entity.Prescription;
import com.healthcare.prescriptionservice.service.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @PostMapping
    public Prescription create(@RequestBody Prescription p) {
        return service.create(p);
    }

    @GetMapping("/{id}")
    public Prescription get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/patient/{id}")
    public List<Prescription> patient(@PathVariable Long id) {
        return service.byPatient(id);
    }

    @GetMapping("/doctor/{id}")
    public List<Prescription> doctor(@PathVariable Long id) {
        return service.byDoctor(id);
    }
}