package com.lifepill.customerservice.model;

import java.util.Objects;

public class ConfirmPaymentRequest {
    private String paymentIntentId;
    private String paymentMethodId;

    public ConfirmPaymentRequest() {
    }

    public ConfirmPaymentRequest(String paymentIntentId, String paymentMethodId) {
        this.paymentIntentId = paymentIntentId;
        this.paymentMethodId = paymentMethodId;
    }

    public static ConfirmPaymentRequestBuilder builder() {
        return new ConfirmPaymentRequestBuilder();
    }

    public static class ConfirmPaymentRequestBuilder {
        private String paymentIntentId;
        private String paymentMethodId;

        ConfirmPaymentRequestBuilder() {
        }

        public ConfirmPaymentRequestBuilder paymentIntentId(String paymentIntentId) {
            this.paymentIntentId = paymentIntentId;
            return this;
        }

        public ConfirmPaymentRequestBuilder paymentMethodId(String paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public ConfirmPaymentRequest build() {
            return new ConfirmPaymentRequest(paymentIntentId, paymentMethodId);
        }
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmPaymentRequest that = (ConfirmPaymentRequest) o;
        return Objects.equals(paymentIntentId, that.paymentIntentId) && Objects.equals(paymentMethodId, that.paymentMethodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentIntentId, paymentMethodId);
    }

    @Override
    public String toString() {
        return "ConfirmPaymentRequest{" +
                "paymentIntentId='" + paymentIntentId + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                '}';
    }
}
