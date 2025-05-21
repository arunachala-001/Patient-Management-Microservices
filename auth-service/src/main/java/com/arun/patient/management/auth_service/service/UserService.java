package com.arun.patient.management.auth_service.service;

import com.arun.patient.management.auth_service.model.User;
import com.arun.patient.management.auth_service.repository.UserRepository;
import com.arun.patient.management.auth_service.request.LoginRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmailAddress(String email) {
        return userRepository.findByEmail(email);
    }
}
