package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.DTO.CartItemDTO;
import com.example.demo.model.DTO.CartItemResponse;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200") // Angular runs on port 4200
public class CartController {

    @Autowired
    private CartService cartService;

    // ✅ Get cart items for a specific user
    @GetMapping("/{userId}")
    public List<CartItemDTO> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItemsByUserId(userId);
    }

    // ✅ Add product to cart
    @PostMapping("/{userId}/add")
    public Cart addToCart(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return cartService.addToCart(userId, productId, quantity);
    }

    // ✅ Remove item from cart by cartId
    @DeleteMapping("/{cartId}")
    public void removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
    }

    // ✅ Clear all cart items for a user
    @DeleteMapping("/{userId}/clear")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
