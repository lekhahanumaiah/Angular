
package com.example.demo.model.DTO;

import lombok.Data;

@Data
public class OrderItemDTO {
    private String productName;
    private int quantity;
    private double price;

    public String getProductName() {
        return  productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getProductPrice() {
        return price;
    }
}
