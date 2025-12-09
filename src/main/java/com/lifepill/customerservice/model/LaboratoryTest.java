package com.lifepill.customerservice.model;

import java.time.LocalDate;
import java.util.Objects;

public class LaboratoryTest {
    private String laboratoryTestName;
    private LocalDate laboratoryTestDate;
    private LocalDate nextLaboratoryTestDate;

    public LaboratoryTest() {
    }

    public LaboratoryTest(String laboratoryTestName, LocalDate laboratoryTestDate, LocalDate nextLaboratoryTestDate) {
        this.laboratoryTestName = laboratoryTestName;
        this.laboratoryTestDate = laboratoryTestDate;
        this.nextLaboratoryTestDate = nextLaboratoryTestDate;
    }

    public static LaboratoryTestBuilder builder() {
        return new LaboratoryTestBuilder();
    }

    public static class LaboratoryTestBuilder {
        private String laboratoryTestName;
        private LocalDate laboratoryTestDate;
        private LocalDate nextLaboratoryTestDate;

        LaboratoryTestBuilder() {
        }

        public LaboratoryTestBuilder laboratoryTestName(String laboratoryTestName) {
            this.laboratoryTestName = laboratoryTestName;
            return this;
        }

        public LaboratoryTestBuilder laboratoryTestDate(LocalDate laboratoryTestDate) {
            this.laboratoryTestDate = laboratoryTestDate;
            return this;
        }

        public LaboratoryTestBuilder nextLaboratoryTestDate(LocalDate nextLaboratoryTestDate) {
            this.nextLaboratoryTestDate = nextLaboratoryTestDate;
            return this;
        }

        public LaboratoryTest build() {
            return new LaboratoryTest(laboratoryTestName, laboratoryTestDate, nextLaboratoryTestDate);
        }
    }

    public String getLaboratoryTestName() {
        return laboratoryTestName;
    }

    public void setLaboratoryTestName(String laboratoryTestName) {
        this.laboratoryTestName = laboratoryTestName;
    }

    public LocalDate getLaboratoryTestDate() {
        return laboratoryTestDate;
    }

    public void setLaboratoryTestDate(LocalDate laboratoryTestDate) {
        this.laboratoryTestDate = laboratoryTestDate;
    }

    public LocalDate getNextLaboratoryTestDate() {
        return nextLaboratoryTestDate;
    }

    public void setNextLaboratoryTestDate(LocalDate nextLaboratoryTestDate) {
        this.nextLaboratoryTestDate = nextLaboratoryTestDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaboratoryTest that = (LaboratoryTest) o;
        return Objects.equals(laboratoryTestName, that.laboratoryTestName) && Objects.equals(laboratoryTestDate, that.laboratoryTestDate) && Objects.equals(nextLaboratoryTestDate, that.nextLaboratoryTestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(laboratoryTestName, laboratoryTestDate, nextLaboratoryTestDate);
    }

    @Override
    public String toString() {
        return "LaboratoryTest{" +
                "laboratoryTestName='" + laboratoryTestName + '\'' +
                ", laboratoryTestDate=" + laboratoryTestDate +
                ", nextLaboratoryTestDate=" + nextLaboratoryTestDate +
                '}';
    }
}
