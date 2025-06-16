package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Cart;
import com.example.demo.model.CartRequest;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("")
    public void addToCart(@RequestBody CartRequest cartRequest) {
        cartService.addToCart(cartRequest.getUserId(), cartRequest.getProductId(), cartRequest.getQuantity());
    }

    @DeleteMapping("")
    public void removeFromCart(@RequestBody CartRequest cartRequest) {
        cartService.removeFromCart(cartRequest.getUserId(), cartRequest.getProductId());
    }

}