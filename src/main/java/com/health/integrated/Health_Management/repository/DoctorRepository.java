package com.health.integrated.Health_Management.repository;

import com.health.integrated.Health_Management.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DoctorRepository extends MongoRepository<Doctor,String> {
    Optional<Doctor> findByUserId(String userId);

    boolean existsByUserId(String userId);
    boolean existsByLicenseNumber(String licenseNumber);
}
