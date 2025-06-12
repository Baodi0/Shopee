package com.example.demo.controller;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        Cart cart = cartService.getOrCreateCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItemToCart(
            @PathVariable String userId,
            @RequestParam String productId,
            @RequestParam int quantity) {
        try {
            Cart updatedCart = cartService.addItemToCart(userId, productId, quantity);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Xử lý lỗi cụ thể hơn
        }
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<Cart> updateItemInCart(
            @PathVariable String userId,
            @RequestParam String productId,
            @RequestParam int newQuantity) {
        try {
            Cart updatedCart = cartService.updateItemQuantityInCart(userId, productId, newQuantity);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<Void> removeItemFromCart(
            @PathVariable String userId,
            @RequestParam String productId) {
        cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }
}