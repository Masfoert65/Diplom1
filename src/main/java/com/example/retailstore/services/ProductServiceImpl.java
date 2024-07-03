package com.example.retailstore.services;

import com.example.retailstore.models.Product;
import com.example.retailstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setImgUrl(updatedProduct.getImgUrl());
            existingProduct.setCategory(updatedProduct.getCategory());
            productRepository.save(existingProduct);
        }
    }

    @Override
    public void updateProduct(Long productId, Double newPrice, Integer newQuantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setPrice(newPrice.intValue()); // Устанавливаем цену, приводя double к int
            product.setQuantity(newQuantity); // Устанавливаем количество
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Map<Long, Product> getAllProductsMap() {
        List<Product> products = productRepository.findAll();
        return products.stream().collect(Collectors.toMap(Product::getId, p -> p));
    }
}
