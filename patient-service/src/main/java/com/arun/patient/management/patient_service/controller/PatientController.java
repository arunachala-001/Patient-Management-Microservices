package com.arun.patient.management.patient_service.controller;

import com.arun.patient.management.patient_service.requestDto.PatientRequest;
import com.arun.patient.management.patient_service.responseDto.PatientResponse;
import com.arun.patient.management.patient_service.service.PatientService;
import com.arun.patient.management.patient_service.util.PatientUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PatientUtil.BASE_URL)
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponse>> retrievePatients() {
        return ResponseEntity.ok().body(patientService.getPatients());
    }

    @PostMapping
    @Operation(summary = "Save Patients")
    public ResponseEntity<PatientResponse> storePatients(@Valid @RequestBody PatientRequest patientRequest) {
        PatientResponse response = patientService.savePatients(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
