package com.health.integrated.Health_Management.dto.response;

import com.health.integrated.Health_Management.enums.Role;
import com.health.integrated.Health_Management.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private UserStatus status;
}