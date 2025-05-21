package com.arun.patient.management.auth_service.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @NotBlank(message = "Email is required") @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required") @Size(min = 8, message = "Must be at least 8 characters long")
        String password
) {}
