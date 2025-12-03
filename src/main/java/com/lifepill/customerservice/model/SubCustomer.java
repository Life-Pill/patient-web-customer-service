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
}
