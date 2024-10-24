package com.spunit.PaymentIntegration.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentRequest {

    private String token;
    private Long amount;
}

