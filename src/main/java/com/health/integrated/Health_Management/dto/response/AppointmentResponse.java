package com.health.integrated.Health_Management.dto.response;

import com.health.integrated.Health_Management.enums.AppointmentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AppointmentResponse {
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;

}
