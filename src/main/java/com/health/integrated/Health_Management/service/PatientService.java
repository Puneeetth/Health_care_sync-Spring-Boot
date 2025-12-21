package com.health.integrated.Health_Management.service;

import com.health.integrated.Health_Management.dto.request.PatientRequest;
import com.health.integrated.Health_Management.dto.response.PatientResponse;
import com.health.integrated.Health_Management.enums.PatientStatus;
import com.health.integrated.Health_Management.enums.Role;
import com.health.integrated.Health_Management.exception.BadRequestException;
import com.health.integrated.Health_Management.model.Patient;
import com.health.integrated.Health_Management.model.User;
import com.health.integrated.Health_Management.repository.PatientRepository;
import com.health.integrated.Health_Management.repository.UserRepository;
import com.health.integrated.Health_Management.transformers.PatientTransformer;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    // createPatient
    public PatientResponse createPatientProfile(String userId, PatientRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User with id " + userId + " not found"));

        if (user.getRole() != Role.PATIENT) {
            throw new BadRequestException("Only PATIENT users can create patient profiles");
        }
        if (patientRepository.existsByUserId(userId)) {
            throw new BadRequestException("UserId " + userId + " already exists");
        }
        Patient patient = new Patient();
        patient.setUserId(userId);
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setEmergencyContact(request.getEmergencyContact());
        patient.setAddress(request.getAddress());
        patient.setStatus(PatientStatus.ACTIVE);

        Patient savePatient = patientRepository.save(patient);
        return PatientTransformer.patientToPatientResponse(savePatient);
    }

    // GetPatient
    public PatientResponse getMyPatientProfile(String userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("User with id " + userId + " not found"));
        return PatientTransformer.patientToPatientResponse(patient);
    }

    public PatientResponse updatePatientProfile(String userId, PatientRequest request) {

        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("Patient profile not found"));

        if (request.getEmergencyContact() != null) {
            patient.setEmergencyContact(request.getEmergencyContact());
        }

        if (request.getAddress() != null) {
            patient.setAddress(request.getAddress());
        }

        Patient savedPatient = patientRepository.save(patient);
        return PatientTransformer.patientToPatientResponse(savedPatient);
    }

}
