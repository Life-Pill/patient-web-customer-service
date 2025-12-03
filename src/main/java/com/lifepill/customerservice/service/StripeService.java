package com.lifepill.customerservice.service;

import com.lifepill.customerservice.model.PaymentIntentDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }

    public PaymentIntentDTO createPaymentIntent(int amount, String currency) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", currency);
        params.put("payment_method_types", new String[] { "card" });

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return new PaymentIntentDTO(
                paymentIntent.getId(),
                paymentIntent.getClientSecret(),
                paymentIntent.getAmount(),
                paymentIntent.getCurrency(),
                paymentIntent.getStatus());
    }

    public PaymentIntentDTO confirmPaymentIntent(String paymentIntentId, String paymentMethodId)
            throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", paymentMethodId);

        paymentIntent = paymentIntent.confirm(params);

        return new PaymentIntentDTO(
                paymentIntent.getId(),
                paymentIntent.getClientSecret(),
                paymentIntent.getAmount(),
                paymentIntent.getCurrency(),
                paymentIntent.getStatus());
    }
}
