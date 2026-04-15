package com.healthcare.appointmentservice.repository;
import com.healthcare.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndTimeSlot(Long doctorId, String timeSlot);
}