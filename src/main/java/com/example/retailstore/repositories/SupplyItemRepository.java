package com.example.retailstore.repositories;

import com.example.retailstore.models.SupplyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyItemRepository extends JpaRepository<SupplyItem, Long> {
    // Дополнительные методы для работы с SupplyItem, если необходимо
}