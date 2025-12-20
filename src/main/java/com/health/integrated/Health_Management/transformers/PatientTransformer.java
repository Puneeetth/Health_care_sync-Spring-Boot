package com.health.integrated.Health_Management.transformers;

import com.health.integrated.Health_Management.dto.request.PatientRequest;
import com.health.integrated.Health_Management.dto.response.PatientResponse;
import com.health.integrated.Health_Management.enums.PatientStatus;
import com.health.integrated.Health_Management.model.Patient;
import com.health.integrated.Health_Management.repository.PatientRepository;

public class PatientTransformer {
    public static Patient patientRequestToPatient(PatientRequest req){
        return Patient.builder()
                .dateOfBirth(req.getDateOfBirth())
                .gender(req.getGender())
                .bloodGroup(req.getBloodGroup())
                .emergencyContact(req.getEmergencyContact())
                .address(req.getAddress())
                .status(PatientStatus.ACTIVE)
                .build();
    }
    public static PatientResponse patientToPatientResponse(Patient patient){
        return PatientResponse.builder()
                .id(patient.getId())
                .userId(patient.getUserId())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .bloodGroup(patient.getBloodGroup())
                .emergencyContact(patient.getEmergencyContact())
                .address(patient.getAddress())
                .status(patient.getStatus())
                .build();
    }

}
