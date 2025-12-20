package com.health.integrated.Health_Management.controller;

import com.health.integrated.Health_Management.dto.request.DoctorRequest;
import com.health.integrated.Health_Management.dto.response.DoctorResponse;
import com.health.integrated.Health_Management.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }
    @PostMapping("/me")
    public ResponseEntity<DoctorResponse> createDoctorProfile(@RequestHeader("X-USER-ID")String userId, @RequestBody DoctorRequest req){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctorProfile(userId,req));
    }
    @GetMapping("/me")
    public ResponseEntity<DoctorResponse> getMyProfile(
            @RequestHeader("X-USER-ID") String userId) {

        return ResponseEntity.ok(doctorService.getMyDoctorProfile(userId));
    }

    @PatchMapping("/me")
    public ResponseEntity<DoctorResponse> updateMyProfile(
            @RequestHeader("X-USER-ID") String userId,
            @RequestBody DoctorRequest request) {

        return ResponseEntity.ok(doctorService.updateDoctorProfile(userId, request));
    }
}
