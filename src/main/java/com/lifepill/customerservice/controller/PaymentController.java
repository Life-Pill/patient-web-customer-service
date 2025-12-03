package com.lifepill.customerservice.controller;

import com.lifepill.customerservice.model.ConfirmPaymentRequest;
import com.lifepill.customerservice.model.PaymentIntentDTO;
import com.lifepill.customerservice.model.PaymentRequest;
import com.lifepill.customerservice.service.StripeService;
import com.stripe.exception.StripeException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @Tag(name = "post", description = "POST methods of Customer service API")
    @Operation(description = "Create payment intent")
    @PostMapping("/paymentIntent")
    public PaymentIntentDTO createPaymentIntent(@RequestBody PaymentRequest paymentRequest) throws StripeException {
        return stripeService.createPaymentIntent(paymentRequest.getAmount(), paymentRequest.getCurrency());
    }

    @Tag(name = "post", description = "POST methods of Customer service API")
    @Operation(description = "Confirm payment")
    @PostMapping("/confirmPayment")
    public PaymentIntentDTO confirmPaymentIntent(@RequestBody ConfirmPaymentRequest confirmPaymentRequest)
            throws StripeException {
        return stripeService.confirmPaymentIntent(confirmPaymentRequest.getPaymentIntentId(),
                confirmPaymentRequest.getPaymentMethodId());
    }
}