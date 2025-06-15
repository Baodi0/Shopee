package com.example.demo.model;

import lombok.Data;

@Data
public class CartRequest {
    private String userId;
    private String productId;
    private Integer quantity;

    // Constructor
    public CartRequest() {}

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}