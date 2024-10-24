package com.spunit.PaymentIntegration.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.api.key}")
    private String apiKey;

    public String charge(String token, Long amount) {
        Stripe.apiKey = apiKey;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "usd");
        chargeParams.put("source", token);

        try {
            Charge charge = Charge.create(chargeParams);
            return charge.getId();
        } catch (StripeException e) {
            throw new RuntimeException("Payment failed: " + e.getMessage());
        }
    }
}

