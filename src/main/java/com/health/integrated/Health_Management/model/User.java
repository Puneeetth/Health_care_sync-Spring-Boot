package com.health.integrated.Health_Management.model;

import com.health.integrated.Health_Management.enums.Role;
import com.health.integrated.Health_Management.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private UserStatus status;
}
