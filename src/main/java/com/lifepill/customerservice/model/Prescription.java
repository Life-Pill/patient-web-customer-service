package com.lifepill.customerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "savedPrescriptions")
public class Prescription {
    @Id
    private String id;
    private Long customerId;
    private String prescriptionImageId;
    private String description;

    public Prescription() {
    }

    public Prescription(String id, Long customerId, String prescriptionImageId, String description) {
        this.id = id;
        this.customerId = customerId;
        this.prescriptionImageId = prescriptionImageId;
        this.description = description;
    }

    public static PrescriptionBuilder builder() {
        return new PrescriptionBuilder();
    }

    public static class PrescriptionBuilder {
        private String id;
        private Long customerId;
        private String prescriptionImageId;
        private String description;

        PrescriptionBuilder() {
        }

        public PrescriptionBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PrescriptionBuilder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public PrescriptionBuilder prescriptionImageId(String prescriptionImageId) {
            this.prescriptionImageId = prescriptionImageId;
            return this;
        }

        public PrescriptionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Prescription build() {
            return new Prescription(id, customerId, prescriptionImageId, description);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPrescriptionImageId() {
        return prescriptionImageId;
    }

    public void setPrescriptionImageId(String prescriptionImageId) {
        this.prescriptionImageId = prescriptionImageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(id, that.id) && Objects.equals(customerId, that.customerId) && Objects.equals(prescriptionImageId, that.prescriptionImageId) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, prescriptionImageId, description);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id='" + id + '\'' +
                ", customerId=" + customerId +
                ", prescriptionImageId='" + prescriptionImageId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
