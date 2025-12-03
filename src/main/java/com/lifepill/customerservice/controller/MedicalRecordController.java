package com.lifepill.customerservice.controller;

import com.lifepill.customerservice.model.MedicalRecord;
import com.lifepill.customerservice.service.MedicalRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    // get a medical record
    @Tag(name = "get", description = "GET methods of Customer service API")
    @Operation(description = "Get a specific medical record")
    @GetMapping("/{patientId}/{recordId}")
    public Optional<MedicalRecord> getMedicalRecord(@PathVariable Long patientId, @PathVariable String recordId) {
        return medicalRecordService.getMedicalRecord(patientId, recordId);
    }

    // add new medical record
    @Tag(name = "post", description = "POST methods of Customer service API")
    @Operation(description = "Create a new medical record")
    @PostMapping
    public MedicalRecord addNewMedicalRecord(@RequestBody MedicalRecord newMedicalRecord) {
        return medicalRecordService.addNewMedicalRecord(newMedicalRecord);
    }

    // update a medical record
    @Tag(name = "put", description = "PUT methods of Customer service API")
    @Operation(description = "Update an existing medical record")
    @PutMapping("/{patientId}/{recordId}")
    public MedicalRecord updateMedicalRecord(@PathVariable Long patientId, @PathVariable String recordId,
            @RequestBody MedicalRecord updatedMedicalRecord) {
        return medicalRecordService.updateMedicalRecord(patientId, recordId, updatedMedicalRecord);
    }

    // delete a medical record
    @Tag(name = "delete", description = "DELETE methods of Customer service API")
    @Operation(description = "Delete a specific medical record")
    @DeleteMapping("/{patientId}/{recordId}")
    public String deleteMedicalRecord(@PathVariable Long patientId, @PathVariable String recordId) {
        medicalRecordService.deleteMedicalRecord(patientId, recordId);

        return "Medical Record Deleted Successfully";
    }
}
