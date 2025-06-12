package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.mongodb.CartRepository;
import com.example.demo.repository.mongodb.ProductRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository; 
    private final RedisTemplate<String, Cart> redisTemplate; 

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, RedisTemplate<String, Cart> redisTemplate) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.redisTemplate = redisTemplate;
    }

    public Cart getOrCreateCart(String userId) {
        Cart cart = redisTemplate.opsForValue().get("cart:" + userId);
        if (cart != null) {
            return cart;
        }

        cart = cartRepository.findByUserID(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserID(userId);
            cart.setSanPham(new ArrayList<>());
        }
        redisTemplate.opsForValue().set("cart:" + userId, cart, 30, TimeUnit.MINUTES); // Cache 30 phút
        return cart;
    }

    public Cart addItemToCart(String userId, String productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        if (product.getSoLuong() < quantity) {
            throw new RuntimeException("Sản phẩm không đủ số lượng trong kho");
        }

        Cart cart = getOrCreateCart(userId);
        Optional<Cart.CartItem> existingItem = cart.getSanPham().stream()
            .filter(item -> item.getIdSanPham().equals(productId))
            .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setSoLuong(existingItem.get().getSoLuong() + quantity);
        } else {
            Cart.CartItem newItem = new Cart.CartItem();
            newItem.setIdSanPham(productId);
            newItem.setSoLuong(quantity);
            newItem.setGiaLucThem(product.getGia());
            cart.getSanPham().add(newItem);
        }
        cart.setNgayCapNhat(LocalDateTime.now());
        Cart savedCart = cartRepository.save(cart); 
        redisTemplate.opsForValue().set("cart:" + userId, savedCart, 30, TimeUnit.MINUTES); 
        return savedCart;
    }

    public Cart updateItemQuantityInCart(String userId, String productId, int newQuantity) {

        return null; 
    }

    public void removeItemFromCart(String userId, String productId) {

    }
}