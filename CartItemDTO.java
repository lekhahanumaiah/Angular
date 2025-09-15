package com.example.demo.model.DTO;

public class CartItemDTO {
    private Long cartId;
    private Long productId;
    private String productName;
    private Double productPrice;
    private String productImageUrl;
    private int quantity;
    private Long userId;   // ✅ new field

    public CartItemDTO() {}

    public CartItemDTO(Long cartId, Long productId, String productName,
                       Double productPrice, String productImageUrl,
                       int quantity, Long userId) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
        this.quantity = quantity;
        this.userId = userId;
    }

    // ✅ Getters and Setters
    public Long getCartId() { return cartId; }
    public void setCartId(Long cartId) { this.cartId = cartId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getProductPrice() { return productPrice; }
    public void setProductPrice(Double productPrice) { this.productPrice = productPrice; }

    public String getProductImageUrl() { return productImageUrl; }
    public void setProductImageUrl(String productImageUrl) { this.productImageUrl = productImageUrl; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
