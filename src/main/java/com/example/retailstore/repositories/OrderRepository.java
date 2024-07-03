package com.example.retailstore.repositories;

import com.example.retailstore.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByStatus(String status);
}
