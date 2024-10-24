package com.spunit.PaymentIntegration.controller;

import com.spunit.PaymentIntegration.request.PaymentRequest;
import com.spunit.PaymentIntegration.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/charge")
    public ResponseEntity<String> charge(@RequestBody PaymentRequest paymentRequest) {
        String chargeId = paymentService.charge(paymentRequest.getToken(), paymentRequest.getAmount());
        return ResponseEntity.ok(chargeId);
    }

}

