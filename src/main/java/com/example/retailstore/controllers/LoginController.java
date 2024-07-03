package com.example.retailstore.controllers;

import com.example.retailstore.models.Product;
import com.example.retailstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {
    private final ProductService productService;

    @Autowired
    public LoginController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/Edicted_product")
    public String editedProduct() {
        return "edited_product";
    }

    @GetMapping("/Authorization")
    public String loginForm() {
        return "Authorization";
    }

    @PostMapping("/Authorization")
    public String authenticate(@RequestParam String username, @RequestParam String password) {
        if ("root".equals(username)) {
            return "redirect:/admin";
        } else {
            return "redirect:/";
        }
    }

}
