package com.health.integrated.Health_Management.controller;

import com.health.integrated.Health_Management.dto.request.AppointmentRequest;
import com.health.integrated.Health_Management.dto.response.AppointmentResponse;
import com.health.integrated.Health_Management.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // PATIENT books
    @PostMapping("/book")
    public ResponseEntity<AppointmentResponse> book(@RequestHeader("X-USER-ID") String userId,
            @RequestBody AppointmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.bookAppointment(userId, request));

    }

    // DOCTOR confirms
    @PatchMapping("/{id}/confirm")
    public ResponseEntity<AppointmentResponse> confirm(
            @RequestHeader("X-USER-ID") String userId,
            @PathVariable String id) {

        return ResponseEntity.ok(
                appointmentService.confirmAppointment(userId, id));
    }
}
