package com.healthcare.doctorservice.controller;
import com.healthcare.doctorservice.entity.Doctor;
import com.healthcare.doctorservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorRepository repository;
    
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return repository.save(doctor);
    }
    
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }
}