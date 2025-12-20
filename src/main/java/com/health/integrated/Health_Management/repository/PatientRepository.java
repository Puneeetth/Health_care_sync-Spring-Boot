package com.health.integrated.Health_Management.repository;

import com.health.integrated.Health_Management.model.Patient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, Id> {
    boolean existsByUserId(String id);
    Optional<Patient> findByUserId(String id);
}
