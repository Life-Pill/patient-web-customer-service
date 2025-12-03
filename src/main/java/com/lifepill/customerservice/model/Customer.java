package com.lifepill.customerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
