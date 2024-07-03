package com.example.retailstore.services;

import com.example.retailstore.models.Order;
import com.example.retailstore.models.OrderItem;
import com.example.retailstore.models.Product;
import com.example.retailstore.models.User;
import com.example.retailstore.repositories.OrderItemRepository;
import com.example.retailstore.repositories.OrderRepository;
import com.example.retailstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BascetService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BascetService(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void addToBascet(Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity should be greater than zero");
        }

        // Получить продукт по ID
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Получить или создать текущий заказ
        Order order = getCurrentOrder();

        // Проверить, есть ли продукт в корзине
        OrderItem existingItem = orderItemRepository.findByOrderAndProduct(order, product);
        if (existingItem != null) {
            // Увеличить количество, если продукт уже в корзине
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            orderItemRepository.save(existingItem);
        } else {
            // Создать новый элемент заказа
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(product.getPrice()); // Предположим, что цена продукта фиксирована
            orderItemRepository.save(orderItem);
        }
    }

    public void clearBascet() {
        // Получить текущий заказ
        Order order = getCurrentOrder();
        // Удалить все элементы заказа
        List<OrderItem> items = orderItemRepository.findByOrder(order);
        orderItemRepository.deleteAll(items);
    }

    public Order getCurrentOrder() {
        String activeStatus = "ACTIVE"; // Предположим, что это статус для активного заказа

        // Найти активный заказ
        Optional<Order> optionalOrder = orderRepository.findByStatus(activeStatus);
        Order order;
        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
        } else {
            // Если активного заказа нет, создать новый
            order = new Order();
            order.setStatus(activeStatus);

            // Добавить фиктивные данные клиента (замените на реальные данные пользователя)
            order.setCustomerName("Иван Иванов");
            order.setCustomerPhone("1234567890");
            order.setCustomerAddress("ул. Пушкина, д. Колотушкина");

            // Установить пользователя (фиктивный пользователь)
            User user = new User();
            user.setId(1L); // Замените 1L на актуальный user_id
            order.setUser(user);

            orderRepository.save(order);
        }
        return order;
    }

    public Iterable<OrderItem> getAllOrderItems(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    public void addToBascet(Long productId) {
        addToBascet(productId, 1);
    }

    public void updateOrderItemQuantity(Long itemId, int quantity) {
        OrderItem orderItem = orderItemRepository.findById(itemId)
                .orElse(null); // Используем orElse(null) вместо orElseThrow
        if (orderItem == null) {
            throw new IllegalArgumentException("OrderItem not found");
        }

        if (quantity <= 0) {
            orderItemRepository.delete(orderItem);
        } else {
            orderItem.setQuantity(quantity);
            orderItemRepository.save(orderItem);
        }
    }

}
