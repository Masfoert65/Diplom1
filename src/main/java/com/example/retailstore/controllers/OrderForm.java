package com.example.retailstore.controllers;

import java.util.List;

public class OrderForm {

    private List<OrderItemForm> orderItems;

    // геттеры и сеттеры
    public List<OrderItemForm> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemForm> orderItems) {
        this.orderItems = orderItems;
    }
}
