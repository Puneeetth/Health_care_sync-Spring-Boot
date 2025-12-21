package com.health.integrated.Health_Management.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    private String doctorId;
    private LocalDateTime appointmentTime;
}
