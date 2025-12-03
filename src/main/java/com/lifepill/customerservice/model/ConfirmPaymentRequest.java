package com.lifepill.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmPaymentRequest {
    private String paymentIntentId;
    private String paymentMethodId;
}
