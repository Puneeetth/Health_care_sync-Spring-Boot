package com.health.integrated.Health_Management.dto.response;

import com.health.integrated.Health_Management.enums.DoctorStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorResponse {
    private String id;
    private String userId;
    private String licenseNumber;
    private String specialization;
    private Integer yearsOfExperience;
    private Double consultationFee;
    private DoctorStatus status;
}
