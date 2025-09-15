package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.DTO.CartItemDTO;
import com.example.demo.model.Order;
import com.example.demo.model.Products;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Get cart items as CartItemDTO list for a user
    public List<CartItemDTO> getCartItemsByUserId(Long userId) {
        List<Cart> cartList = cartRepository.findByUserIdWithProducts(userId);

        return cartList.stream()
                .map(cart -> new CartItemDTO(
                        cart.getId(),
                        cart.getProduct().getId(),
                        cart.getProduct().getName(),
                        cart.getProduct().getPrice(),
                        cart.getProduct().getImageUrl(),
                        cart.getQuantity(),
                        cart.getUserId()
                ))
                .collect(Collectors.toList());
    }

    // Add a product to the cart
    public Cart addToCart(Long userId, Long productId, int quantity) {
        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = new Cart(product, userId, quantity);
        return cartRepository.save(cart);
    }

    // Remove cart item by id
    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }

    // Clear all cart items for a user
    public void clearCart(Long userId) {
        List<Cart> cartItems = cartRepository.findByUserIdWithProducts(userId);
        cartRepository.deleteAll(cartItems);
    }

    // Place an order and clear the cart
    public Order placeOrder(Long userId, String paymentMode) {
        List<Cart> cartItems = cartRepository.findByUserIdWithProducts(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty for user " + userId);
        }

        Order order = new Order(userId, paymentMode, "PLACED", cartItems);

        // Set order reference in cart items
        for (Cart item : cartItems) {
            item.setOrder(order);
        }

        Order savedOrder = orderRepository.save(order);

        // Clear the cart after saving the order
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }
}
