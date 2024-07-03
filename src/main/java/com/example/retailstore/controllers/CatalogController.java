package com.example.retailstore.controllers;

import com.example.retailstore.models.Product;
import com.example.retailstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private final ProductService productService;

    @Autowired
    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showCatalog(Model model) {
        Map<Long, Product> products = productService.getAllProductsMap();
        model.addAttribute("products", products.values());
        model.addAttribute("catalogForm", new CatalogForm(products.keySet()));
        return "catalog";
    }

    @PostMapping("/save")
    public String saveCatalogChanges(@ModelAttribute("catalogForm") CatalogForm catalogForm) {
        // Преобразуем Map<Long, Double> и Map<Long, Integer> в Long, Double, Integer
        catalogForm.getProductPrices().forEach((productId, price) -> {
            Integer quantity = catalogForm.getProductQuantities().getOrDefault(productId, 0);
            productService.updateProduct(productId, price, quantity);
        });
        return "redirect:/catalog/";
    }

    @PostMapping("/delete/{productId}")
    @ResponseBody
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return "Product deleted successfully";
    }
}
