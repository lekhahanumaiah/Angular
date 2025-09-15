package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // tie cart to user
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;

    private int quantity;

    // order relation (nullable until order is placed)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Cart() {}

    public Cart(Products product, Long userId, int quantity) {
        this.product = product;
        this.userId = userId;
        this.quantity = quantity;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Products getProduct() { return product; }
    public void setProduct(Products product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
