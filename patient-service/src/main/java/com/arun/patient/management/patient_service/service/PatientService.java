package com.arun.patient.management.patient_service.service;

import com.arun.patient.management.patient_service.exception.EmailAlreadyExistsException;
import com.arun.patient.management.patient_service.grpc.BillingServiceGrpcClient;
import com.arun.patient.management.patient_service.kafka.KafkaProducer;
import com.arun.patient.management.patient_service.mapper.PatientMapper;
import com.arun.patient.management.patient_service.model.Patient;
import com.arun.patient.management.patient_service.repository.PatientRepository;
import com.arun.patient.management.patient_service.requestDto.PatientRequest;
import com.arun.patient.management.patient_service.responseDto.PatientResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    private final BillingServiceGrpcClient grpcClient;

    private final KafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper,
                          BillingServiceGrpcClient grpcClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.grpcClient = grpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<PatientResponse> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper::mapToPatientResponseDto)
                .toList();
    }

//    @Transactional
    public PatientResponse savePatients(PatientRequest patientRequest) throws EmailAlreadyExistsException {
        if(patientRepository.existsByEmail(patientRequest.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email address already found "+ patientRequest.getEmail());
        }

        Patient patient = patientRepository.save(patientMapper.mapToPatient(patientRequest));
        grpcClient.createBillingAccounts(patient.getId().toString(), patient.getName(), patient.getEmail());
        kafkaProducer.sendEvent(patient);
        return patientMapper.mapToPatientResponseDto(patient);

    }
}
