package com.ctse.paymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctse.paymentservice.models.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private List<Payment> payments = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    @GetMapping
    public List<Payment> getAllPayments() {
        return payments;
    }

    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestBody Map<String, Object> paymentRequest) {
        Payment payment = new Payment();
        payment.setId(idCounter.getAndIncrement());
        payment.setOrderId((Integer) paymentRequest.get("orderId"));
        payment.setAmount((Double) paymentRequest.get("amount"));
        payment.setMethod((String) paymentRequest.get("method"));
        payment.setStatus("SUCCESS");
        
        payments.add(payment);
        return ResponseEntity.status(201).body(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        return payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
