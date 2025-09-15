package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    public Order placeOrder(Long userId, String paymentMode) {
        // fetch user's cart
        List<Cart> cartItems = cartRepository.findByUserIdWithProducts(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty for user " + userId);
        }

        // create order
        Order order = new Order(userId, paymentMode, "PLACED", cartItems);

        // set order reference in cart items
        for (Cart item : cartItems) {
            item.setOrder(order);
        }

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
