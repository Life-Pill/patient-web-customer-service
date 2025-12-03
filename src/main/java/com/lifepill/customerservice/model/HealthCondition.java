package com.lifepill.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthCondition {
    private String healthConditionName;
    private LocalDate treatmentStartedDate;
    private List<Medication> medication;
    private ChannelingDetails channelingDetails;

    //Medication class used in HealthCondition class
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Medication {
        private String drugName;
        private String dosage;
        private String frequency;
        private boolean drugUsageStatus;
        private LocalDate startedDate;
        private LocalDate stoppedDate;
    }

    //ChannelingDetails class used in HealthCondition class
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChannelingDetails {
        private String doctorName;
        private LocalDate lastDoctorVisit;
        private LocalDate nextDoctorVisit;
    }
}
