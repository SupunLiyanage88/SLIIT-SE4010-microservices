package com.ctse.orderservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ctse.orderservice.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private List<Order> orders = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    @GetMapping
    public List<Order> getAllOrders() {
        return orders;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setId(idCounter.getAndIncrement());
        order.setStatus("PENDING");
        orders.add(order);
        return ResponseEntity.status(201).body(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
