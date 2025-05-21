package com.arun.patient.management.patient_service.service;

import com.arun.patient.management.patient_service.exception.EmailAlreadyExistsException;
import com.arun.patient.management.patient_service.grpc.BillingServiceGrpcClient;
import com.arun.patient.management.patient_service.kafka.KafkaProducer;
import com.arun.patient.management.patient_service.mapper.PatientMapper;
import com.arun.patient.management.patient_service.model.Patient;
import com.arun.patient.management.patient_service.repository.PatientRepository;
import com.arun.patient.management.patient_service.requestDto.PatientRequest;
import com.arun.patient.management.patient_service.responseDto.PatientResponse;
import com.arun.patient.management.patient_service.util.PatientUtilityTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    PatientService patientService;

    @Mock
    PatientRepository patientRepository;

    @Mock
    PatientMapper patientMapper;

    @Mock
    BillingServiceGrpcClient grpcClient;

    @Mock
    KafkaProducer kafkaProducer;

    @Test
    void test_retrievingPatientRecords() {
        Mockito.when(patientRepository.findAll()).thenReturn(PatientUtilityTest.patient);
        List<PatientResponse> patientResponse = patientService.getPatients();
        Assertions.assertEquals(2, patientResponse.size());
    }

    @Test
    void test_savingPatientRecord() {
        Patient patient = PatientUtilityTest.patient.stream().findFirst().orElse(null);
        Mockito.when(patientMapper.mapToPatient(PatientUtilityTest.patientRequest)).thenReturn(patient);
        Mockito.when(patientRepository.save(patient)).thenReturn(patient);
        Mockito.when(patientMapper.mapToPatientResponseDto(patient))
                .thenReturn(PatientUtilityTest.patientResponse);

        Assertions.assertEquals(PatientUtilityTest.patientResponse,
                patientService.savePatients(PatientUtilityTest.patientRequest));

        Mockito.when(patientRepository.existsByEmail(PatientUtilityTest.patientRequest.getEmail()))
                .thenReturn(true);

        Assertions.assertThrows(EmailAlreadyExistsException.class,
                () -> patientService.savePatients(PatientUtilityTest.patientRequest));
    }
}
