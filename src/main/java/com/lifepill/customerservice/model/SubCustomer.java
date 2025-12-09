package com.lifepill.customerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "subCustomer")
public class SubCustomer {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subCus_id")
    private Long subCustomerId;

    @NotNull
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "full_name", nullable = false, length = 200)
    private String subCustomerFullName;

    @Size(min = 1, max = 20)
    @Column(name = "nic", nullable = true, unique = true, length = 20)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String subCustomerNIC;

    public SubCustomer() {
    }

    public SubCustomer(Long subCustomerId, Long parentId, String subCustomerFullName, String subCustomerNIC) {
        this.subCustomerId = subCustomerId;
        this.parentId = parentId;
        this.subCustomerFullName = subCustomerFullName;
        this.subCustomerNIC = subCustomerNIC;
    }

    public static SubCustomerBuilder builder() {
        return new SubCustomerBuilder();
    }

    public static class SubCustomerBuilder {
        private Long subCustomerId;
        private Long parentId;
        private String subCustomerFullName;
        private String subCustomerNIC;

        SubCustomerBuilder() {
        }

        public SubCustomerBuilder subCustomerId(Long subCustomerId) {
            this.subCustomerId = subCustomerId;
            return this;
        }

        public SubCustomerBuilder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        public SubCustomerBuilder subCustomerFullName(String subCustomerFullName) {
            this.subCustomerFullName = subCustomerFullName;
            return this;
        }

        public SubCustomerBuilder subCustomerNIC(String subCustomerNIC) {
            this.subCustomerNIC = subCustomerNIC;
            return this;
        }

        public SubCustomer build() {
            return new SubCustomer(subCustomerId, parentId, subCustomerFullName, subCustomerNIC);
        }
    }

    public Long getSubCustomerId() {
        return subCustomerId;
    }

    public void setSubCustomerId(Long subCustomerId) {
        this.subCustomerId = subCustomerId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSubCustomerFullName() {
        return subCustomerFullName;
    }

    public void setSubCustomerFullName(String subCustomerFullName) {
        this.subCustomerFullName = subCustomerFullName;
    }

    public String getSubCustomerNIC() {
        return subCustomerNIC;
    }

    public void setSubCustomerNIC(String subCustomerNIC) {
        this.subCustomerNIC = subCustomerNIC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCustomer that = (SubCustomer) o;
        return Objects.equals(subCustomerId, that.subCustomerId) && Objects.equals(parentId, that.parentId) && Objects.equals(subCustomerFullName, that.subCustomerFullName) && Objects.equals(subCustomerNIC, that.subCustomerNIC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCustomerId, parentId, subCustomerFullName, subCustomerNIC);
    }

    @Override
    public String toString() {
        return "SubCustomer{" +
                "subCustomerId=" + subCustomerId +
                ", parentId=" + parentId +
                ", subCustomerFullName='" + subCustomerFullName + '\'' +
                ", subCustomerNIC='" + subCustomerNIC + '\'' +
                '}';
    }
}
