package com.lifepill.customerservice.controller;

import com.lifepill.customerservice.model.ChargeRequest;
import com.lifepill.customerservice.repo.ChargeRequestRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Value("${stripe.currency}")
    private String currency;

    @Autowired
    private ChargeRequestRepository chargeRequestRepository;

    @PostMapping("/charge")
    public ResponseEntity<String> chargeCard(@RequestBody ChargeRequest chargeRequest) {
        try {
            ChargeCreateParams createParams = new ChargeCreateParams.Builder()
                    .setAmount(chargeRequest.getAmount())
                    .setCurrency(currency)
                    .setSource(chargeRequest.getToken())
                    .build();

            Charge charge = Charge.create(createParams);
            chargeRequestRepository.save(chargeRequest);
            return ResponseEntity.ok("Payment successful: " + charge.getId());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed: " + e.getMessage());
        }
    }
}