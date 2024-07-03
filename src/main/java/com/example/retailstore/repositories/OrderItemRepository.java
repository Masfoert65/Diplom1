package com.example.retailstore.repositories;

import com.example.retailstore.models.Order;
import com.example.retailstore.models.OrderItem;
import com.example.retailstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByOrderAndProduct(Order order, Product product);
    List<OrderItem> findByOrder(Order order);
    Optional<OrderItem> findById(Long id);
}
