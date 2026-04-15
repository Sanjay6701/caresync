package com.healthcare.appointmentservice.service;

import com.healthcare.appointmentservice.entity.Appointment;
import com.healthcare.appointmentservice.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Appointment createAppointment(Appointment appointment) {
        appointment.setStatus("BOOKED");
        return repository.save(appointment);
    }

    // GET BY ID
    public Appointment getAppointmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found with id: " + id));
    }

    // GET ALL
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }
}
