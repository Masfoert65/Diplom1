package com.example.retailstore.util;

import com.example.retailstore.models.OrderItem;

import java.util.List;

public class OrderCalculationUtil {

    public static double calculateTotal(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
