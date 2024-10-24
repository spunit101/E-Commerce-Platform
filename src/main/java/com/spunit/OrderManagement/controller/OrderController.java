package com.spunit.OrderManagement.controller;

import com.spunit.OrderManagement.model.Orders;
import com.spunit.OrderManagement.model.OrderItem;
import com.spunit.OrderManagement.service.OrderService;
import com.spunit.UserManagement.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Orders> createOrder(@AuthenticationPrincipal AuthUser authUser, @RequestBody List<OrderItem> items) {
        Orders orders = orderService.createOrder(authUser, items);
        return ResponseEntity.status(201).body(orders);
    }

    @GetMapping
    public List<Orders> getOrders(@AuthenticationPrincipal AuthUser authUser) {
        return orderService.getOrdersByUser(authUser);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Orders> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        Orders updatedOrders = orderService.updateOrderStatus(orderId, status);
        return updatedOrders != null ? ResponseEntity.ok(updatedOrders) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
