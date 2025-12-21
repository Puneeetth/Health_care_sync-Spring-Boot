package com.health.integrated.Health_Management.service;

import com.health.integrated.Health_Management.dto.request.AppointmentRequest;
import com.health.integrated.Health_Management.dto.response.AppointmentResponse;
import com.health.integrated.Health_Management.enums.AppointmentStatus;
import com.health.integrated.Health_Management.enums.Role;
import com.health.integrated.Health_Management.exception.BadRequestException;
import com.health.integrated.Health_Management.model.Appointment;
import com.health.integrated.Health_Management.model.Doctor;
import com.health.integrated.Health_Management.model.Patient;
import com.health.integrated.Health_Management.model.User;
import com.health.integrated.Health_Management.repository.AppointmentRepository;
import com.health.integrated.Health_Management.repository.DoctorRepository;
import com.health.integrated.Health_Management.repository.PatientRepository;
import com.health.integrated.Health_Management.repository.UserRepository;
import com.health.integrated.Health_Management.transformers.AppointmentTransformer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            UserRepository userRepository
    ) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }
    //user book appointment
    public AppointmentResponse bookAppointment(String userId, AppointmentRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BadRequestException("User with " + userId + " not found"));

        if(user.getRole() != Role.PATIENT){
            throw new BadRequestException("Only PATIENT can book appointment");
        }
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(()-> new BadRequestException("Patient profile not found"));

                Doctor doctor = doctorRepository.findById(request.getDoctorId())
                        .orElseThrow(()->new BadRequestException("Doctor with " + request.getDoctorId() + "not found"));

                boolean alreadyBooked = appointmentRepository.existsByDoctorIdAndAppointmentTimeAndStatusIn(
                        doctor.getId(),request.getAppointmentTime(), List.of(AppointmentStatus.REQUESTED,
                                AppointmentStatus.CONFIRMED)

                );
                if(alreadyBooked){
                    throw new BadRequestException("Doctor already booked for this time slot");
                }

        Appointment appointment = AppointmentTransformer.appointmentRequestToAppointment(patient.getId(),request);
                Appointment saved = appointmentRepository.save(appointment);

                return AppointmentTransformer.appointmentToAppointmentResponse(saved);
    }
    //Doctor Confirms appointment

    public AppointmentResponse confirmAppointment(String doctorUserId,String appointmentId){
        User user = userRepository.findById(doctorUserId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        if (user.getRole() != Role.DOCTOR) {
            throw new BadRequestException("Only DOCTOR can confirm appointment");
        }
        Doctor doctor = doctorRepository.findByUserId(doctorUserId)
                .orElseThrow(() -> new BadRequestException("Doctor profile not found"));

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new BadRequestException("Appointment not found"));

        if(!appointment.getDoctorId().equals(doctor.getId())){
            throw new BadRequestException("Unauthorized");
        }
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        Appointment appointmentConfirmed = appointmentRepository.save(appointment);
        return AppointmentTransformer.appointmentToAppointmentResponse(appointmentConfirmed);
    }
}
