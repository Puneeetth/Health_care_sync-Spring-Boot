package com.health.integrated.Health_Management.controller;

import com.health.integrated.Health_Management.dto.request.PatientRequest;
import com.health.integrated.Health_Management.dto.response.PatientResponse;
import com.health.integrated.Health_Management.model.Patient;
import com.health.integrated.Health_Management.service.PatientService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }
    @PostMapping("/create")
    public ResponseEntity<PatientResponse> createMyProfile(@RequestHeader("X-USER-ID") String userId,
                                                           @RequestBody PatientRequest req){
   return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatientProfile(userId,req));
    }
    @GetMapping("/me")
    public ResponseEntity<PatientResponse> getMyPatientProfile(@RequestHeader("X-USER-ID") String userId){
       PatientResponse response = patientService.getMyPatientProfile(userId);
       return ResponseEntity.ok(response);
    }
    @PatchMapping("/me")
    public ResponseEntity<PatientResponse> updatePatentProfile(@RequestHeader("X-USER-ID") String userId,
                                                               @RequestBody PatientRequest req){
        PatientResponse response = patientService.updatePatientProfile(userId,req);

        return ResponseEntity.ok(response);
    }
}
