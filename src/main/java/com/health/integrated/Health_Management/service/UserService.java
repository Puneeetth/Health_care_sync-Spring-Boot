package com.health.integrated.Health_Management.service;

import com.health.integrated.Health_Management.dto.request.UserRequest;
import com.health.integrated.Health_Management.dto.response.UserResponse;
import com.health.integrated.Health_Management.enums.UserStatus;
import com.health.integrated.Health_Management.exception.BadRequestException;
import com.health.integrated.Health_Management.model.User;
import com.health.integrated.Health_Management.repository.UserRepository;
import com.health.integrated.Health_Management.transformers.UserTransformer;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create user
    public UserResponse createUser(UserRequest request) {

        if (request.getEmail() != null) {
            userRepository.findByEmail(request.getEmail())
                    .ifPresent(user -> {
                        throw new BadRequestException("Email already exists");
                    });
        }

        if (request.getPhone() != null) {
            userRepository.findByPhone(request.getPhone())
                    .ifPresent(user -> {
                        throw new BadRequestException("Phone already exists");
                    });
        }

        User user = UserTransformer.userRequestToUser(request);
        User savedUser = userRepository.save(user);
        user.setStatus(UserStatus.ACTIVE);

        return UserTransformer.userToUserResponse(savedUser);
    }

    // get user
    public UserResponse getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new BadRequestException("User with id " + id + " not found");
        }
        return UserTransformer.userToUserResponse(user.get());
    }
    //update user
    public UserResponse updateUser(String id ,UserRequest request){
       User user = userRepository.findById(id).
               orElseThrow(()-> new BadRequestException("User with Id " + id + " not found"));
        if(request.getEmail() != null && userRepository.existsByEmailAndIdNot(request.getEmail(), id)){
            throw new BadRequestException("Email already Exists");
        }
        if(request.getPhone() != null && userRepository.existsByPhoneAndIdNot(request.getPhone(),id)){
            throw new BadRequestException("Phone Number already Exists");
        }
        if(request.getName() != null){
            user.setName(request.getName());
        }
        if(request.getEmail() != null){
            user.setEmail(request.getEmail());
        }
        if(request.getPhone() != null){
            user.setPhone((request.getPhone()));
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        if (user.getStatus() == null) {
            user.setStatus(UserStatus.ACTIVE);
        }
        User updatedUser = userRepository.save(user);
        return UserTransformer.userToUserResponse(updatedUser);
    }

    //delete user
    public void deleteUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new BadRequestException("User with id " + id + " not found")
                );

        userRepository.deleteById(id);
    }


}
