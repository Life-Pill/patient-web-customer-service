package com.lifepill.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "medicalRecords")
@Data
@AllArgsConstructor
@Builder
public class MedicalRecord {
    @Id
    private String id;
    private boolean subAccount;
    private Long patientId;
    private int patientAge;
    private List<HealthCondition> healthConditions;
    private List<String> allergies;
    private List<LaboratoryTest> laboratories;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public MedicalRecord() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
    }

    public MedicalRecord(boolean subAccount, Long patientId, int patientAge, List<HealthCondition> healthConditions,
            List<String> allergies, List<LaboratoryTest> laboratories) {
        this.subAccount = subAccount;
        this.patientId = patientId;
        this.patientAge = patientAge;
        this.healthConditions = healthConditions;
        this.allergies = allergies;
        this.laboratories = laboratories;
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
    }
}
