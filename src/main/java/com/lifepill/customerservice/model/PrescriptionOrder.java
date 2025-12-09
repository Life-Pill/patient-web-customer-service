package com.lifepill.customerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Document(collection = "prescriptionOrders")
public class PrescriptionOrder {
    @Id
    private String id;
    private Long customerId;
    private String prescriptionId;
    private String prescriptionImageId;
    private Map<Long, String> availablePharmacies;
    private Long selectedPharmacyId;
    private boolean orderStatus;
    private String customerMessage;
    private LocalDateTime createdOn;

    public PrescriptionOrder() {
        this.createdOn = LocalDateTime.now();
    }

    public PrescriptionOrder(Long customerId, String prescriptionId, boolean orderStatus) {
        this.customerId = customerId;
        this.prescriptionId = prescriptionId;
        this.orderStatus = orderStatus;
        this.createdOn = LocalDateTime.now();
    }

    public PrescriptionOrder(String id, Long customerId, String prescriptionId, String prescriptionImageId, Map<Long, String> availablePharmacies, Long selectedPharmacyId, boolean orderStatus, String customerMessage, LocalDateTime createdOn) {
        this.id = id;
        this.customerId = customerId;
        this.prescriptionId = prescriptionId;
        this.prescriptionImageId = prescriptionImageId;
        this.availablePharmacies = availablePharmacies;
        this.selectedPharmacyId = selectedPharmacyId;
        this.orderStatus = orderStatus;
        this.customerMessage = customerMessage;
        this.createdOn = createdOn;
    }
    
    // Builder Pattern
    public static PrescriptionOrderBuilder builder() {
        return new PrescriptionOrderBuilder();
    }

    public static class PrescriptionOrderBuilder {
        private String id;
        private Long customerId;
        private String prescriptionId;
        private String prescriptionImageId;
        private Map<Long, String> availablePharmacies;
        private Long selectedPharmacyId;
        private boolean orderStatus;
        private String customerMessage;
        private LocalDateTime createdOn;

        PrescriptionOrderBuilder() {
        }

        public PrescriptionOrderBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PrescriptionOrderBuilder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public PrescriptionOrderBuilder prescriptionId(String prescriptionId) {
            this.prescriptionId = prescriptionId;
            return this;
        }

        public PrescriptionOrderBuilder prescriptionImageId(String prescriptionImageId) {
            this.prescriptionImageId = prescriptionImageId;
            return this;
        }

        public PrescriptionOrderBuilder availablePharmacies(Map<Long, String> availablePharmacies) {
            this.availablePharmacies = availablePharmacies;
            return this;
        }

        public PrescriptionOrderBuilder selectedPharmacyId(Long selectedPharmacyId) {
            this.selectedPharmacyId = selectedPharmacyId;
            return this;
        }

        public PrescriptionOrderBuilder orderStatus(boolean orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public PrescriptionOrderBuilder customerMessage(String customerMessage) {
            this.customerMessage = customerMessage;
            return this;
        }

        public PrescriptionOrderBuilder createdOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public PrescriptionOrder build() {
            return new PrescriptionOrder(id, customerId, prescriptionId, prescriptionImageId, availablePharmacies, selectedPharmacyId, orderStatus, customerMessage, createdOn);
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

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionImageId() {
        return prescriptionImageId;
    }

    public void setPrescriptionImageId(String prescriptionImageId) {
        this.prescriptionImageId = prescriptionImageId;
    }

    public Map<Long, String> getAvailablePharmacies() {
        return availablePharmacies;
    }

    public void setAvailablePharmacies(Map<Long, String> availablePharmacies) {
        this.availablePharmacies = availablePharmacies;
    }

    public Long getSelectedPharmacyId() {
        return selectedPharmacyId;
    }

    public void setSelectedPharmacyId(Long selectedPharmacyId) {
        this.selectedPharmacyId = selectedPharmacyId;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionOrder that = (PrescriptionOrder) o;
        return orderStatus == that.orderStatus && Objects.equals(id, that.id) && Objects.equals(customerId, that.customerId) && Objects.equals(prescriptionId, that.prescriptionId) && Objects.equals(prescriptionImageId, that.prescriptionImageId) && Objects.equals(availablePharmacies, that.availablePharmacies) && Objects.equals(selectedPharmacyId, that.selectedPharmacyId) && Objects.equals(customerMessage, that.customerMessage) && Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, prescriptionId, prescriptionImageId, availablePharmacies, selectedPharmacyId, orderStatus, customerMessage, createdOn);
    }

    @Override
    public String toString() {
        return "PrescriptionOrder{" +
                "id='" + id + '\'' +
                ", customerId=" + customerId +
                ", prescriptionId='" + prescriptionId + '\'' +
                ", prescriptionImageId='" + prescriptionImageId + '\'' +
                ", availablePharmacies=" + availablePharmacies +
                ", selectedPharmacyId=" + selectedPharmacyId +
                ", orderStatus=" + orderStatus +
                ", customerMessage='" + customerMessage + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
