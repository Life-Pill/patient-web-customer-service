package com.lifepill.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaboratoryTest {
    private String laboratoryTestName;
    private LocalDate laboratoryTestDate;
    private LocalDate nextLaboratoryTestDate;
}
