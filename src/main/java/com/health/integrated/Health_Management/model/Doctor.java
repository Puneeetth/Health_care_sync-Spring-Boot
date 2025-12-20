package com.health.integrated.Health_Management.model;

import com.health.integrated.Health_Management.enums.DoctorStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "doctors")
public class Doctor {
    @Id
    private String id;
    @Indexed(unique = true)
    private String userId;
    @Indexed(unique = true)
    private String licenseNumber;
    private String specialization;
    private Integer yearsOfExperience;
    private double consultationFee;
    private DoctorStatus status;
}
