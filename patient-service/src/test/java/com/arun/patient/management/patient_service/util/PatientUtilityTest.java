package com.arun.patient.management.patient_service.util;

import com.arun.patient.management.patient_service.model.Patient;
import com.arun.patient.management.patient_service.requestDto.PatientRequest;
import com.arun.patient.management.patient_service.responseDto.PatientResponse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PatientUtilityTest {

    public static final PatientRequest patientRequest = new PatientRequest(
            "Arun",
            "arun@gmail.com",
            "chennai",
            "2001-01-01"
    );
    public static final PatientResponse patientResponse = new PatientResponse(
                    "6e525dc3-5519-46b9-90a9-91428af010e2",
                    "Arun",
                    "arun@gmail.com",
                    "chennai",
                    "2001-01-01"
    );

    public static final List<Patient> patient = Arrays.asList(
            new Patient(UUID.randomUUID(), "Arun", "arun@gmail.com",
                    "chennai", LocalDate.of(2001,01,01)),
            new Patient(UUID.randomUUID(), "Aruna", "aruna@gmail.com",
                    "chennai", LocalDate.of(2001,01,01))
    );

}
