package com.example.retailstore.services;

import com.example.retailstore.models.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void updateProduct(Long id, Product updatedProduct);
    void updateProduct(Long productId, Double newPrice, Integer newQuantity);
    void deleteProduct(Long id);
    Map<Long, Product> getAllProductsMap();
}
