package com.lifepill.customerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private Long customerId;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "full_name", nullable = false, length = 200)
    private String customerFullName;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String customerEmail;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "mobile_number", nullable = false, unique = true, length = 50)
    private String customerMobileNumber;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cus_password", nullable = false, length = 200)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String customerPassword;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address_street", nullable = false, length = 200)
    private String customerAddressStreet;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address_city", nullable = false, length = 200)
    private String customerAddressCity;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address_district", nullable = false, length = 100)
    private String customerAddressDistrict;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nic", length = 20)
    private String customerNIC;

    public Customer() {
    }

    public Customer(Long customerId, String customerFullName, String customerEmail, String customerMobileNumber, String customerPassword, String customerAddressStreet, String customerAddressCity, String customerAddressDistrict, String customerNIC) {
        this.customerId = customerId;
        this.customerFullName = customerFullName;
        this.customerEmail = customerEmail;
        this.customerMobileNumber = customerMobileNumber;
        this.customerPassword = customerPassword;
        this.customerAddressStreet = customerAddressStreet;
        this.customerAddressCity = customerAddressCity;
        this.customerAddressDistrict = customerAddressDistrict;
        this.customerNIC = customerNIC;
    }

    // Builder Pattern
    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public static class CustomerBuilder {
        private Long customerId;
        private String customerFullName;
        private String customerEmail;
        private String customerMobileNumber;
        private String customerPassword;
        private String customerAddressStreet;
        private String customerAddressCity;
        private String customerAddressDistrict;
        private String customerNIC;

        CustomerBuilder() {
        }

        public CustomerBuilder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public CustomerBuilder customerFullName(String customerFullName) {
            this.customerFullName = customerFullName;
            return this;
        }

        public CustomerBuilder customerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public CustomerBuilder customerMobileNumber(String customerMobileNumber) {
            this.customerMobileNumber = customerMobileNumber;
            return this;
        }

        public CustomerBuilder customerPassword(String customerPassword) {
            this.customerPassword = customerPassword;
            return this;
        }

        public CustomerBuilder customerAddressStreet(String customerAddressStreet) {
            this.customerAddressStreet = customerAddressStreet;
            return this;
        }

        public CustomerBuilder customerAddressCity(String customerAddressCity) {
            this.customerAddressCity = customerAddressCity;
            return this;
        }

        public CustomerBuilder customerAddressDistrict(String customerAddressDistrict) {
            this.customerAddressDistrict = customerAddressDistrict;
            return this;
        }

        public CustomerBuilder customerNIC(String customerNIC) {
            this.customerNIC = customerNIC;
            return this;
        }

        public Customer build() {
            return new Customer(customerId, customerFullName, customerEmail, customerMobileNumber, customerPassword, customerAddressStreet, customerAddressCity, customerAddressDistrict, customerNIC);
        }
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerAddressStreet() {
        return customerAddressStreet;
    }

    public void setCustomerAddressStreet(String customerAddressStreet) {
        this.customerAddressStreet = customerAddressStreet;
    }

    public String getCustomerAddressCity() {
        return customerAddressCity;
    }

    public void setCustomerAddressCity(String customerAddressCity) {
        this.customerAddressCity = customerAddressCity;
    }

    public String getCustomerAddressDistrict() {
        return customerAddressDistrict;
    }

    public void setCustomerAddressDistrict(String customerAddressDistrict) {
        this.customerAddressDistrict = customerAddressDistrict;
    }

    public String getCustomerNIC() {
        return customerNIC;
    }

    public void setCustomerNIC(String customerNIC) {
        this.customerNIC = customerNIC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) && Objects.equals(customerFullName, customer.customerFullName) && Objects.equals(customerEmail, customer.customerEmail) && Objects.equals(customerMobileNumber, customer.customerMobileNumber) && Objects.equals(customerPassword, customer.customerPassword) && Objects.equals(customerAddressStreet, customer.customerAddressStreet) && Objects.equals(customerAddressCity, customer.customerAddressCity) && Objects.equals(customerAddressDistrict, customer.customerAddressDistrict) && Objects.equals(customerNIC, customer.customerNIC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerFullName, customerEmail, customerMobileNumber, customerPassword, customerAddressStreet, customerAddressCity, customerAddressDistrict, customerNIC);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerFullName='" + customerFullName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerMobileNumber='" + customerMobileNumber + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", customerAddressStreet='" + customerAddressStreet + '\'' +
                ", customerAddressCity='" + customerAddressCity + '\'' +
                ", customerAddressDistrict='" + customerAddressDistrict + '\'' +
                ", customerNIC='" + customerNIC + '\'' +
                '}';
    }
}
