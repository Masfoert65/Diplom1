package com.example.retailstore.controllers;

import java.util.Map;
import java.util.Set;

public class CatalogForm {

    private Map<Long, Double> productPrices;
    private Map<Long, Integer> productQuantities;

    public CatalogForm(Map<Long, Double> productPrices, Map<Long, Integer> productQuantities) {
        this.productPrices = productPrices;
        this.productQuantities = productQuantities;
    }

    public CatalogForm(Set<Long> longs) {
    }

    public Map<Long, Double> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(Map<Long, Double> productPrices) {
        this.productPrices = productPrices;
    }

    public Map<Long, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<Long, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }
}
