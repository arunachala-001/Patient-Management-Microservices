package com.arun.patient.management.auth_service.service;

import com.arun.patient.management.auth_service.request.LoginRequestDto;
import com.arun.patient.management.auth_service.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDto loginRequestDto) {
        Optional<String> token = userService.findByEmailAddress(loginRequestDto.email())
                .filter(u -> passwordEncoder.matches(loginRequestDto.password(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }

    public boolean validateToken(String token) {
        try{
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }
}
