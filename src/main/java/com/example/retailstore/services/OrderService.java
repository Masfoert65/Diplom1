package com.example.retailstore.services;

import com.example.retailstore.models.Order;

import java.util.List;

public interface OrderService {
    Order getOrderById(Long id);
    Order saveOrder(Order order);
    void deleteOrder(Long id);
    void closeOrder(Long orderId);
    Order save(Order order);
    List<Order> getAllOrders();
}
