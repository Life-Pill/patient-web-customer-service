package com.lifepill.customerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Document(collection = "medicalRecords")
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

    public MedicalRecord(String id, boolean subAccount, Long patientId, int patientAge, List<HealthCondition> healthConditions, List<String> allergies, List<LaboratoryTest> laboratories, LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.id = id;
        this.subAccount = subAccount;
        this.patientId = patientId;
        this.patientAge = patientAge;
        this.healthConditions = healthConditions;
        this.allergies = allergies;
        this.laboratories = laboratories;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public static MedicalRecordBuilder builder() {
        return new MedicalRecordBuilder();
    }

    public static class MedicalRecordBuilder {
        private String id;
        private boolean subAccount;
        private Long patientId;
        private int patientAge;
        private List<HealthCondition> healthConditions;
        private List<String> allergies;
        private List<LaboratoryTest> laboratories;
        private LocalDateTime createdOn;
        private LocalDateTime updatedOn;

        MedicalRecordBuilder() {
        }

        public MedicalRecordBuilder id(String id) {
            this.id = id;
            return this;
        }

        public MedicalRecordBuilder subAccount(boolean subAccount) {
            this.subAccount = subAccount;
            return this;
        }

        public MedicalRecordBuilder patientId(Long patientId) {
            this.patientId = patientId;
            return this;
        }

        public MedicalRecordBuilder patientAge(int patientAge) {
            this.patientAge = patientAge;
            return this;
        }

        public MedicalRecordBuilder healthConditions(List<HealthCondition> healthConditions) {
            this.healthConditions = healthConditions;
            return this;
        }

        public MedicalRecordBuilder allergies(List<String> allergies) {
            this.allergies = allergies;
            return this;
        }

        public MedicalRecordBuilder laboratories(List<LaboratoryTest> laboratories) {
            this.laboratories = laboratories;
            return this;
        }

        public MedicalRecordBuilder createdOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public MedicalRecordBuilder updatedOn(LocalDateTime updatedOn) {
            this.updatedOn = updatedOn;
            return this;
        }

        public MedicalRecord build() {
            return new MedicalRecord(id, subAccount, patientId, patientAge, healthConditions, allergies, laboratories, createdOn, updatedOn);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSubAccount() {
        return subAccount;
    }

    public void setSubAccount(boolean subAccount) {
        this.subAccount = subAccount;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public List<HealthCondition> getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(List<HealthCondition> healthConditions) {
        this.healthConditions = healthConditions;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<LaboratoryTest> getLaboratories() {
        return laboratories;
    }

    public void setLaboratories(List<LaboratoryTest> laboratories) {
        this.laboratories = laboratories;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalRecord that = (MedicalRecord) o;
        return subAccount == that.subAccount && patientAge == that.patientAge && Objects.equals(id, that.id) && Objects.equals(patientId, that.patientId) && Objects.equals(healthConditions, that.healthConditions) && Objects.equals(allergies, that.allergies) && Objects.equals(laboratories, that.laboratories) && Objects.equals(createdOn, that.createdOn) && Objects.equals(updatedOn, that.updatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subAccount, patientId, patientAge, healthConditions, allergies, laboratories, createdOn, updatedOn);
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id='" + id + '\'' +
                ", subAccount=" + subAccount +
                ", patientId=" + patientId +
                ", patientAge=" + patientAge +
                ", healthConditions=" + healthConditions +
                ", allergies=" + allergies +
                ", laboratories=" + laboratories +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
