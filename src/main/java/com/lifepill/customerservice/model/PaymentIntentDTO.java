package com.lifepill.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentIntentDTO {
    private String id;
    private String clientSecret;
    private long amount;
    private String currency;
    private String status;
}
