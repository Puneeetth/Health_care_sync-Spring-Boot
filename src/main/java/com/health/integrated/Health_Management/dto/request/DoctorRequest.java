package com.health.integrated.Health_Management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequest {
    private String licenseNumber;
    private String specialization;
    private Integer yearsOfExperience;
    private Double consultationFee;
}
