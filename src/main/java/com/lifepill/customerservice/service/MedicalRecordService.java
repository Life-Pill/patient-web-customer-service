package com.lifepill.customerservice.service;

import com.lifepill.customerservice.model.MedicalRecord;
import com.lifepill.customerservice.repo.MedicalRecordRepository;
import com.lifepill.customerservice.util.MissingParameterException;
import com.lifepill.customerservice.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    // get a medical record
    public Optional<MedicalRecord> getMedicalRecord(Long patientId, String recordId) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(recordId);

        if (medicalRecord.isEmpty()) {
            throw new ResourceNotFoundException("Medical Record with ID " + recordId + " not found.");
        }

        MedicalRecord existingMedicalRecord = medicalRecord.get();

        if (!patientId.equals(existingMedicalRecord.getPatientId())) {
            throw new ResourceNotFoundException("Medical Record with ID " + recordId + " not found.");
        } else {
            return medicalRecord;
        }
    }

    // add new medical record
    public MedicalRecord addNewMedicalRecord(MedicalRecord newMedicalRecord) {
        // Empty patientId error handling
        if (newMedicalRecord.getPatientId().toString().isEmpty()) {
            throw new MissingParameterException("Patient ID cannot be Empty");
        }

        return medicalRecordRepository.save(newMedicalRecord);
    }

    // update a medical record
    public MedicalRecord updateMedicalRecord(Long patientId, String recordId, MedicalRecord updatedMedicalRecord) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(recordId);

        if (medicalRecord.isEmpty()) {
            throw new ResourceNotFoundException("Medical Record with ID " + recordId + " not found.");
        }

        MedicalRecord existingMedicalRecord = medicalRecord.get();

        if (!patientId.equals(existingMedicalRecord.getPatientId())) {
            throw new ResourceNotFoundException("Medical Record with ID " + recordId + " not found.");
        }

        medicalRecordRepository.deleteById(recordId);
        updatedMedicalRecord.setId(existingMedicalRecord.getId());
        updatedMedicalRecord.setCreatedOn(existingMedicalRecord.getCreatedOn());

        // Empty patientId error handling
        if (updatedMedicalRecord.getPatientId().toString().isEmpty()) {
            throw new MissingParameterException("Patient ID cannot be Empty");
        }

        medicalRecordRepository.save(updatedMedicalRecord);

        return updatedMedicalRecord;
    }

    // delete a medical record
    public void deleteMedicalRecord(Long patientId, String recordId) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(recordId);

        if (medicalRecord.isEmpty()) {
            throw new ResourceNotFoundException("Medical Record with ID " + recordId + " not found.");
        }

        MedicalRecord existingMedicalRecord = medicalRecord.get();

        if (!patientId.equals(existingMedicalRecord.getPatientId())) {
            throw new ResourceNotFoundException("Medical Record with ID " + recordId + " not found.");
        }

        medicalRecordRepository.deleteById(recordId);
    }
}
