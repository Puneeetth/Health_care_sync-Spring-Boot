package com.health.integrated.Health_Management.repository;

import com.health.integrated.Health_Management.enums.AppointmentStatus;
import com.health.integrated.Health_Management.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment,String> {

    boolean existsByDoctorIdAndAppointmentTimeAndStatusIn(
            String doctorId, LocalDateTime appointmentTime, List<AppointmentStatus> statuses
            );
    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctorId(String doctorId);
}
