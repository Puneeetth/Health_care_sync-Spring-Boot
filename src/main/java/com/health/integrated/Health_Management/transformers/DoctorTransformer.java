package com.health.integrated.Health_Management.transformers;

import com.health.integrated.Health_Management.dto.request.DoctorRequest;
import com.health.integrated.Health_Management.dto.response.DoctorResponse;
import com.health.integrated.Health_Management.model.Doctor;

public class DoctorTransformer {
    public static Doctor doctorRequestToDoctor(DoctorRequest req){
        return Doctor.builder()
                .licenseNumber(req.getLicenseNumber())
                .specialization(req.getSpecialization())
                .yearsOfExperience(req.getYearsOfExperience())
                .consultationFee(req.getConsultationFee())
                .build();
    }
    public static DoctorResponse doctorToDoctorResponse(Doctor doctor){
        return DoctorResponse.builder()
                .id(doctor.getId())
                .userId(doctor.getUserId())
                .licenseNumber(doctor.getLicenseNumber())
                .specialization(doctor.getSpecialization())
                .yearsOfExperience(doctor.getYearsOfExperience())
                .consultationFee(doctor.getConsultationFee())
                .status(doctor.getStatus())
                .build();
    }
}
