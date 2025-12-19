package com.health.integrated.Health_Management.repository;

import com.health.integrated.Health_Management.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    boolean existsByEmailAndIdNot(String email,String id);
    boolean existsByPhoneAndIdNot(String phone,String id);
}
