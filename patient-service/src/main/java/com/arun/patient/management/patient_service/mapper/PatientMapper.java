package com.arun.patient.management.patient_service.mapper;

import com.arun.patient.management.patient_service.model.Patient;
import com.arun.patient.management.patient_service.requestDto.PatientRequest;
import com.arun.patient.management.patient_service.responseDto.PatientResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientMapper {

    public PatientResponse mapToPatientResponseDto(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .birthDate(patient.getBirthDate().toString())
                .build();
    }

    public Patient mapToPatient(PatientRequest patientRequest) {
        return Patient.builder()
                .name(patientRequest.getName())
                .email(patientRequest.getEmail())
                .address(patientRequest.getAddress())
                .birthDate(LocalDate.parse(patientRequest.getBirthDate()))
                .build();
    }
}
