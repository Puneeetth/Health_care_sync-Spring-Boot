package com.health.integrated.Health_Management.dto.request;

import com.health.integrated.Health_Management.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotNull
    private Role role;
}
