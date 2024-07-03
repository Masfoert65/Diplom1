package com.example.retailstore.repositories;

import com.example.retailstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Дополнительные методы для работы с товарами, если нужно
}
