package com.healthcare.appointmentservice.controller;

import com.healthcare.appointmentservice.entity.Appointment;
import com.healthcare.appointmentservice.service.AppointmentService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // CREATE appointment
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment saved = service.createAppointment(appointment);
            return ResponseEntity.ok(saved);

        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Double booking! Slot already taken."));
        }
    }

    // GET appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAppointmentById(id));
    }

    // GET all appointments
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(service.getAllAppointments());
    }
}