package com.lifepill.customerservice.model;

import java.util.Objects;

public class PaymentIntentDTO {
    private String id;
    private String clientSecret;
    private long amount;
    private String currency;
    private String status;

    public PaymentIntentDTO() {
    }

    public PaymentIntentDTO(String id, String clientSecret, long amount, String currency, String status) {
        this.id = id;
        this.clientSecret = clientSecret;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }

    public static PaymentIntentDTOBuilder builder() {
        return new PaymentIntentDTOBuilder();
    }

    public static class PaymentIntentDTOBuilder {
        private String id;
        private String clientSecret;
        private long amount;
        private String currency;
        private String status;

        PaymentIntentDTOBuilder() {
        }

        public PaymentIntentDTOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PaymentIntentDTOBuilder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public PaymentIntentDTOBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public PaymentIntentDTOBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public PaymentIntentDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public PaymentIntentDTO build() {
            return new PaymentIntentDTO(id, clientSecret, amount, currency, status);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentIntentDTO that = (PaymentIntentDTO) o;
        return amount == that.amount && Objects.equals(id, that.id) && Objects.equals(clientSecret, that.clientSecret) && Objects.equals(currency, that.currency) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientSecret, amount, currency, status);
    }

    @Override
    public String toString() {
        return "PaymentIntentDTO{" +
                "id='" + id + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
