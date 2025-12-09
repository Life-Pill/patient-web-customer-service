package com.lifepill.customerservice.model;

import java.util.Objects;

public class PaymentRequest {
    private int amount;
    private String currency;

    public PaymentRequest() {
    }

    public PaymentRequest(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static PaymentRequestBuilder builder() {
        return new PaymentRequestBuilder();
    }

    public static class PaymentRequestBuilder {
        private int amount;
        private String currency;

        PaymentRequestBuilder() {
        }

        public PaymentRequestBuilder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public PaymentRequestBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public PaymentRequest build() {
            return new PaymentRequest(amount, currency);
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequest that = (PaymentRequest) o;
        return amount == that.amount && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
