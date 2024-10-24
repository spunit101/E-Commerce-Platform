package com.spunit.OrderManagement.service;

import com.spunit.OrderManagement.model.Orders;
import com.spunit.OrderManagement.model.OrderItem;
import com.spunit.OrderManagement.repository.OrderRepository;
import com.spunit.UserManagement.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders createOrder(AuthUser authUser, List<OrderItem> items) {
        BigDecimal totalAmount = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Orders orders = new Orders();
        orders.setAuthUser(authUser);
        orders.setItems(items);
        orders.setTotalAmount(totalAmount);
        orders.setOrderDate(LocalDateTime.now());
        orders.setStatus("PENDING");

        return orderRepository.save(orders);
    }

    public List<Orders> getOrdersByUser(AuthUser authUser) {
        return orderRepository.findByAuthUser(authUser);
    }

    public Orders updateOrderStatus(Long orderId, String status) {
        Orders orders = orderRepository.findById(orderId).orElse(null);
        if (orders != null) {
            orders.setStatus(status);
            return orderRepository.save(orders);
        }
        return null;
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}

