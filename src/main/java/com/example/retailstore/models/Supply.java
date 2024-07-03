package com.example.retailstore.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Supplies")
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "supply_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime supplyDate;

    @Column(name = "total_cost", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalCost;

    // Constructors, Getters, and Setters
    public Supply() {
    }

    public Supply(Supplier supplier, LocalDateTime supplyDate, BigDecimal totalCost) {
        this.supplier = supplier;
        this.supplyDate = supplyDate;
        this.totalCost = totalCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public LocalDateTime getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(LocalDateTime supplyDate) {
        this.supplyDate = supplyDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "id=" + id +
                ", supplier=" + supplier +
                ", supplyDate=" + supplyDate +
                ", totalCost=" + totalCost +
                '}';
    }
}
