package com.arun.patient.management.patient_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String,String> fieldError = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fieldError.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(fieldError);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        log.warn("Email address already exist {}", ex.getMessage());
        Map<String,String> errors = new HashMap<>();
        errors.put("message", "Email address already exist");
        return ResponseEntity.badRequest().body(errors);
    }
}
