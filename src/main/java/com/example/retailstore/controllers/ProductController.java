package com.example.retailstore.controllers;

import com.example.retailstore.models.Category;
import com.example.retailstore.models.Product;
import com.example.retailstore.services.CategoryService;
import com.example.retailstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("newProduct", new Product());
        model.addAttribute("categories", categories);
        return "products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "product-edit-form";
        } else {
            return "product-not-found";
        }
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product";
        } else {
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product updatedProduct) {
        Long categoryId = updatedProduct.getCategory().getId();
        Category category = categoryService.getCategoryById(categoryId);
        updatedProduct.setCategory(category);

        productService.updateProduct(id, updatedProduct);

        return "redirect:/products/list";
    }

    @PostMapping("/save")
    public String saveOrUpdateProduct(@ModelAttribute("product") Product product) {
        Long categoryId = product.getCategory().getId();
        Category category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);

        productService.saveProduct(product);
        return "redirect:/products/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/list";
    }

    @GetMapping("/details/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product-details";
        } else {
            return "error";
        }
    }

}

