package com.healthcare.patientservice.controller;

import com.healthcare.patientservice.entity.Patient;
import com.healthcare.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientRepository repository;
    
    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
    	try {
            Patient saved = repository.save(patient);
            return ResponseEntity.ok(saved);

        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Patient previously visted"));
        }
    }
    
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
    }
    
    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
}