package com.health.integrated.Health_Management.dto.response;

import com.health.integrated.Health_Management.enums.PatientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponse {
    private String id;
    private String userId;
    private LocalDateTime dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String emergencyContact;
    private String address;
    private PatientStatus status;
}
