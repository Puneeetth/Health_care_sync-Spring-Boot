package com.health.integrated.Health_Management.model;

import com.health.integrated.Health_Management.enums.PatientStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "patients")
public class Patient {
    @Id
    private String id;
    @Indexed(unique = true)
    private String userId;
    private LocalDateTime dateOfBirth;
    private String gender;
    private String bloodGroup;
    private String emergencyContact;
    private String address;
    private PatientStatus status;

}
