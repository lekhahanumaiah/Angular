package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders") // "order" is SQL keyword
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String paymentMode;

    private String status; // e.g. "PLACED", "SHIPPED", "DELIVERED"

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> items;

    public Order() {}

    public Order(Long userId, String paymentMode, String status, List<Cart> items) {
        this.userId = userId;
        this.paymentMode = paymentMode;
        this.status = status;
        this.items = items;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Cart> getItems() { return items; }
    public void setItems(List<Cart> items) { this.items = items; }
}
