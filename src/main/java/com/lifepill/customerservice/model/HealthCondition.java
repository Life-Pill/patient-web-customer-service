package com.lifepill.customerservice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class HealthCondition {
    private String healthConditionName;
    private LocalDate treatmentStartedDate;
    private List<Medication> medication;
    private ChannelingDetails channelingDetails;

    public HealthCondition() {
    }

    public HealthCondition(String healthConditionName, LocalDate treatmentStartedDate, List<Medication> medication, ChannelingDetails channelingDetails) {
        this.healthConditionName = healthConditionName;
        this.treatmentStartedDate = treatmentStartedDate;
        this.medication = medication;
        this.channelingDetails = channelingDetails;
    }

    public static HealthConditionBuilder builder() {
        return new HealthConditionBuilder();
    }

    public static class HealthConditionBuilder {
        private String healthConditionName;
        private LocalDate treatmentStartedDate;
        private List<Medication> medication;
        private ChannelingDetails channelingDetails;

        HealthConditionBuilder() {
        }

        public HealthConditionBuilder healthConditionName(String healthConditionName) {
            this.healthConditionName = healthConditionName;
            return this;
        }

        public HealthConditionBuilder treatmentStartedDate(LocalDate treatmentStartedDate) {
            this.treatmentStartedDate = treatmentStartedDate;
            return this;
        }

        public HealthConditionBuilder medication(List<Medication> medication) {
            this.medication = medication;
            return this;
        }

        public HealthConditionBuilder channelingDetails(ChannelingDetails channelingDetails) {
            this.channelingDetails = channelingDetails;
            return this;
        }

        public HealthCondition build() {
            return new HealthCondition(healthConditionName, treatmentStartedDate, medication, channelingDetails);
        }
    }

    public String getHealthConditionName() {
        return healthConditionName;
    }

    public void setHealthConditionName(String healthConditionName) {
        this.healthConditionName = healthConditionName;
    }

    public LocalDate getTreatmentStartedDate() {
        return treatmentStartedDate;
    }

    public void setTreatmentStartedDate(LocalDate treatmentStartedDate) {
        this.treatmentStartedDate = treatmentStartedDate;
    }

    public List<Medication> getMedication() {
        return medication;
    }

    public void setMedication(List<Medication> medication) {
        this.medication = medication;
    }

    public ChannelingDetails getChannelingDetails() {
        return channelingDetails;
    }

    public void setChannelingDetails(ChannelingDetails channelingDetails) {
        this.channelingDetails = channelingDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCondition that = (HealthCondition) o;
        return Objects.equals(healthConditionName, that.healthConditionName) && Objects.equals(treatmentStartedDate, that.treatmentStartedDate) && Objects.equals(medication, that.medication) && Objects.equals(channelingDetails, that.channelingDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthConditionName, treatmentStartedDate, medication, channelingDetails);
    }

    @Override
    public String toString() {
        return "HealthCondition{" +
                "healthConditionName='" + healthConditionName + '\'' +
                ", treatmentStartedDate=" + treatmentStartedDate +
                ", medication=" + medication +
                ", channelingDetails=" + channelingDetails +
                '}';
    }

    //Medication class used in HealthCondition class
    public static class Medication {
        private String drugName;
        private String dosage;
        private String frequency;
        private boolean drugUsageStatus;
        private LocalDate startedDate;
        private LocalDate stoppedDate;

        public Medication() {
        }

        public Medication(String drugName, String dosage, String frequency, boolean drugUsageStatus, LocalDate startedDate, LocalDate stoppedDate) {
            this.drugName = drugName;
            this.dosage = dosage;
            this.frequency = frequency;
            this.drugUsageStatus = drugUsageStatus;
            this.startedDate = startedDate;
            this.stoppedDate = stoppedDate;
        }

        public static MedicationBuilder builder() {
            return new MedicationBuilder();
        }

        public static class MedicationBuilder {
            private String drugName;
            private String dosage;
            private String frequency;
            private boolean drugUsageStatus;
            private LocalDate startedDate;
            private LocalDate stoppedDate;

            MedicationBuilder() {
            }

            public MedicationBuilder drugName(String drugName) {
                this.drugName = drugName;
                return this;
            }

            public MedicationBuilder dosage(String dosage) {
                this.dosage = dosage;
                return this;
            }

            public MedicationBuilder frequency(String frequency) {
                this.frequency = frequency;
                return this;
            }

            public MedicationBuilder drugUsageStatus(boolean drugUsageStatus) {
                this.drugUsageStatus = drugUsageStatus;
                return this;
            }

            public MedicationBuilder startedDate(LocalDate startedDate) {
                this.startedDate = startedDate;
                return this;
            }

            public MedicationBuilder stoppedDate(LocalDate stoppedDate) {
                this.stoppedDate = stoppedDate;
                return this;
            }

            public Medication build() {
                return new Medication(drugName, dosage, frequency, drugUsageStatus, startedDate, stoppedDate);
            }
        }

        public String getDrugName() {
            return drugName;
        }

        public void setDrugName(String drugName) {
            this.drugName = drugName;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public boolean isDrugUsageStatus() {
            return drugUsageStatus;
        }

        public void setDrugUsageStatus(boolean drugUsageStatus) {
            this.drugUsageStatus = drugUsageStatus;
        }

        public LocalDate getStartedDate() {
            return startedDate;
        }

        public void setStartedDate(LocalDate startedDate) {
            this.startedDate = startedDate;
        }

        public LocalDate getStoppedDate() {
            return stoppedDate;
        }

        public void setStoppedDate(LocalDate stoppedDate) {
            this.stoppedDate = stoppedDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Medication that = (Medication) o;
            return drugUsageStatus == that.drugUsageStatus && Objects.equals(drugName, that.drugName) && Objects.equals(dosage, that.dosage) && Objects.equals(frequency, that.frequency) && Objects.equals(startedDate, that.startedDate) && Objects.equals(stoppedDate, that.stoppedDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(drugName, dosage, frequency, drugUsageStatus, startedDate, stoppedDate);
        }

        @Override
        public String toString() {
            return "Medication{" +
                    "drugName='" + drugName + '\'' +
                    ", dosage='" + dosage + '\'' +
                    ", frequency='" + frequency + '\'' +
                    ", drugUsageStatus=" + drugUsageStatus +
                    ", startedDate=" + startedDate +
                    ", stoppedDate=" + stoppedDate +
                    '}';
        }
    }

    //ChannelingDetails class used in HealthCondition class
    public static class ChannelingDetails {
        private String doctorName;
        private LocalDate lastDoctorVisit;
        private LocalDate nextDoctorVisit;

        public ChannelingDetails() {
        }

        public ChannelingDetails(String doctorName, LocalDate lastDoctorVisit, LocalDate nextDoctorVisit) {
            this.doctorName = doctorName;
            this.lastDoctorVisit = lastDoctorVisit;
            this.nextDoctorVisit = nextDoctorVisit;
        }

        public static ChannelingDetailsBuilder builder() {
            return new ChannelingDetailsBuilder();
        }

        public static class ChannelingDetailsBuilder {
            private String doctorName;
            private LocalDate lastDoctorVisit;
            private LocalDate nextDoctorVisit;

            ChannelingDetailsBuilder() {
            }

            public ChannelingDetailsBuilder doctorName(String doctorName) {
                this.doctorName = doctorName;
                return this;
            }

            public ChannelingDetailsBuilder lastDoctorVisit(LocalDate lastDoctorVisit) {
                this.lastDoctorVisit = lastDoctorVisit;
                return this;
            }

            public ChannelingDetailsBuilder nextDoctorVisit(LocalDate nextDoctorVisit) {
                this.nextDoctorVisit = nextDoctorVisit;
                return this;
            }

            public ChannelingDetails build() {
                return new ChannelingDetails(doctorName, lastDoctorVisit, nextDoctorVisit);
            }
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public LocalDate getLastDoctorVisit() {
            return lastDoctorVisit;
        }

        public void setLastDoctorVisit(LocalDate lastDoctorVisit) {
            this.lastDoctorVisit = lastDoctorVisit;
        }

        public LocalDate getNextDoctorVisit() {
            return nextDoctorVisit;
        }

        public void setNextDoctorVisit(LocalDate nextDoctorVisit) {
            this.nextDoctorVisit = nextDoctorVisit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ChannelingDetails that = (ChannelingDetails) o;
            return Objects.equals(doctorName, that.doctorName) && Objects.equals(lastDoctorVisit, that.lastDoctorVisit) && Objects.equals(nextDoctorVisit, that.nextDoctorVisit);
        }

        @Override
        public int hashCode() {
            return Objects.hash(doctorName, lastDoctorVisit, nextDoctorVisit);
        }

        @Override
        public String toString() {
            return "ChannelingDetails{" +
                    "doctorName='" + doctorName + '\'' +
                    ", lastDoctorVisit=" + lastDoctorVisit +
                    ", nextDoctorVisit=" + nextDoctorVisit +
                    '}';
        }
    }
}
