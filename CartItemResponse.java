package com.example.demo.model.DTO;

import com.example.demo.model.Products;

public class CartItemResponse {
    private Long id;
    private int quantity;
    private Products product;

    public CartItemResponse() {}

    public CartItemResponse(Long id, int quantity, Products product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Products getProduct() { return product; }
    public void setProduct(Products product) { this.product = product; }
}
