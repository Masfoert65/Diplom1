package com.example.retailstore.controllers;

import com.example.retailstore.models.Order;
import com.example.retailstore.models.OrderItem;
import com.example.retailstore.models.Product;
import com.example.retailstore.services.OrderService;
import com.example.retailstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/list")
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "Order_users"; // Предполагается, что у вас есть представление с таким именем
    }

    @GetMapping("/add")
    public String showOrderForm(Model model) {
        model.addAttribute("orderForm", new OrderForm());
        model.addAttribute("products", productService.getAllProducts());
        return "add_order";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute("orderForm") OrderForm orderForm, Model model) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("NEW");
        //TODO: Сделай парсинг и добавление в базу.

        // List<OrderItem> orderItems = new ArrayList<>();
        // for (OrderItemForm itemForm : orderForm.getOrderItems()) {
        //     Product product = productService.getProductById(itemForm.getProductId());
        //     if (product != null) {
        //         OrderItem orderItem = new OrderItem();
        //         orderItem.setProduct(product);
        //         orderItem.setQuantity(itemForm.getQuantity());
        //         orderItem.setPrice(product.getPrice());
        //         orderItem.setOrder(order);
        //         orderItems.add(orderItem);
        //     }
        // }
        // order.setItems(orderItems);

        // orderService.saveOrder(order); // Используем метод saveOrder у orderService для сохранения заказа

        // model.addAttribute("message", "Заказ успешно добавлен");
        // return "add_order_success";
        return "1";
    }

    @GetMapping("/close/{orderId}")
    public String closeOrder(@PathVariable Long orderId) {
        orderService.closeOrder(orderId);
        return "redirect:/orders/list";
    }

    // API методы

    @GetMapping("/api/orders")
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrdersAPI() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/api/orders/{id}")
    @ResponseBody
    public ResponseEntity<Order> getOrderByIdAPI(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/orders")
    @ResponseBody
    public ResponseEntity<Order> addOrderAPI(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<Void> deleteOrderAPI(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
