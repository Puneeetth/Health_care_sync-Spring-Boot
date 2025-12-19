package com.health.integrated.Health_Management.transformers;

import com.health.integrated.Health_Management.dto.request.UserRequest;
import com.health.integrated.Health_Management.dto.response.UserResponse;
import com.health.integrated.Health_Management.model.User;

public class UserTransformer {
    public static User userRequestToUser(UserRequest request){
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(request.getRole())
                .build();
    }
    public static UserResponse userToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}
