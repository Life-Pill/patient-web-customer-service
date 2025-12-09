package com.lifepill.customerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "pharmacies")
public class Pharmacy {
    @Id
    private String id;
    private String pharmacyName;

    public Pharmacy() {
    }

    public Pharmacy(String id, String pharmacyName) {
        this.id = id;
        this.pharmacyName = pharmacyName;
    }

    public static PharmacyBuilder builder() {
        return new PharmacyBuilder();
    }

    public static class PharmacyBuilder {
        private String id;
        private String pharmacyName;

        PharmacyBuilder() {
        }

        public PharmacyBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PharmacyBuilder pharmacyName(String pharmacyName) {
            this.pharmacyName = pharmacyName;
            return this;
        }

        public Pharmacy build() {
            return new Pharmacy(id, pharmacyName);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return Objects.equals(id, pharmacy.id) && Objects.equals(pharmacyName, pharmacy.pharmacyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pharmacyName);
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id='" + id + '\'' +
                ", pharmacyName='" + pharmacyName + '\'' +
                '}';
    }
}
