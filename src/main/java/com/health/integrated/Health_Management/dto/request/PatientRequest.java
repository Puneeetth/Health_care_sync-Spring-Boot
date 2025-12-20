package com.health.integrated.Health_Management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private LocalDateTime dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String emergencyContact;
    private String address;
}
