package com.example.retailstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SupplyItems")
public class SupplyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supply_id")
    private Supply supply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    // Constructors

    public SupplyItem() {
    }

    public SupplyItem(Supply supply, Product product, int quantity, BigDecimal cost) {
        this.supply = supply;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    // toString method (optional)

    @Override
    public String toString() {
        return "SupplyItem{" +
                "id=" + id +
                ", supply=" + supply +
                ", product=" + product +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
