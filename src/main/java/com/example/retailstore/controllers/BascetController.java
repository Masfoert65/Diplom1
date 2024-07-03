package com.example.retailstore.controllers;

import com.example.retailstore.models.Order;
import com.example.retailstore.services.BascetService;
import com.example.retailstore.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/bascet")
public class BascetController {

    private final BascetService bascetService;
    private final OrderService orderService;

    @Autowired
    public BascetController(BascetService bascetService, OrderService orderService) {
        this.bascetService = bascetService;
        this.orderService = orderService;
    }

    @PostMapping("/add/{productId}")
    public String addToBascet(@PathVariable Long productId, Model model) {
        bascetService.addToBascet(productId, 1); // Добавление товара с количеством 1
        Order currentOrder = bascetService.getCurrentOrder();
        model.addAttribute("bascetItems", bascetService.getAllOrderItems(currentOrder));
        return "bascet"; // Перенаправление на страницу корзины
    }

    @GetMapping("")
    public String showBascet(Model model) {
        Order currentOrder = bascetService.getCurrentOrder();
        model.addAttribute("bascetItems", bascetService.getAllOrderItems(currentOrder));
        return "bascet"; // Возвращение имени представления Thymeleaf для корзины
    }

    @PostMapping("/checkout")
    @Transactional
    public String checkout(Model model) {
        try {
            Order currentOrder = bascetService.getCurrentOrder();
            currentOrder.setStatus("CHECKED_OUT");
            orderService.save(currentOrder);
            bascetService.clearBascet();
            model.addAttribute("message", "Заказ успешно оформлен, ожидайте связи с менеджером");
            return "redirect:/bascet"; // Перенаправление на страницу корзины
        } catch (Exception e) {
            // Обработка ошибок, например, логирование или уведомление пользователю
            model.addAttribute("error", "Произошла ошибка при оформлении заказа");
            return "error-page"; // Возвращение страницы с сообщением об ошибке
        }
    }


    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateBascet(@RequestBody Map<String, Object> payload) {
        try {
            Long itemId = Long.valueOf(payload.get("itemId").toString());
            int quantity = Integer.parseInt(payload.get("quantity").toString());
            bascetService.updateOrderItemQuantity(itemId, quantity);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException | NullPointerException e) {
            return ResponseEntity.badRequest().build(); // Обработка ошибок входных данных
        }
    }
}
