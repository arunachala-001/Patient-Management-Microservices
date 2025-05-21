package com.arun.patient.management.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            log.info("Received Patient Event: [PatientId={}, PatientName={}, PatientEmail={} ]",
                    patientEvent.getPatientId(), patientEvent.getPatientName(), patientEvent.getEmail());
        }
        catch (InvalidProtocolBufferException e) {
            log.error("Error when Deserializing event {}", e.getMessage());
        }
    }
}
