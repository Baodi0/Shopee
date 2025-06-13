package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CART_CACHE_PREFIX = "cart:";

    public Cart getCartByUserId(String userId) {
        String cacheKey = CART_CACHE_PREFIX + userId;
        Cart cachedCart = (Cart) redisTemplate.opsForValue().get(cacheKey);

        if (cachedCart != null) {
            return cachedCart;
        }

        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isPresent()) {
            redisTemplate.opsForValue().set(cacheKey, cart.get(), 1, TimeUnit.HOURS);
            return cart.get();
        } else {
            Cart newCart = new Cart(userId);
            Cart savedCart = cartRepository.save(newCart);
            redisTemplate.opsForValue().set(cacheKey, savedCart, 1, TimeUnit.HOURS);
            return savedCart;
        }
    }

    public Cart addToCart(String userId, String productId, Integer quantity) {
        Cart cart = getCartByUserId(userId);
        Optional<Product> product = productService.getProductById(productId);

        if (!product.isPresent()) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        }

        Product prod = product.get();

        if (prod.getSoLuong() < quantity) {
            throw new RuntimeException("Không đủ hàng trong kho");
        }

        boolean found = false;
        for (Cart.CartItem item : cart.getSanPham()) {
            if (item.getIdSanPham().equals(productId)) {
                item.setSoLuong(item.getSoLuong() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.getSanPham().add(new Cart.CartItem(productId, quantity, prod.getGia()));
        }

        cart.setNgayCapNhat(java.time.LocalDateTime.now());
        Cart updatedCart = cartRepository.save(cart);

        redisTemplate.opsForValue().set(CART_CACHE_PREFIX + userId, updatedCart, 1, TimeUnit.HOURS);
        return updatedCart;
    }

    public void removeFromCart(String userId, String productId) {
        Cart cart = getCartByUserId(userId);
        cart.getSanPham().removeIf(item -> item.getIdSanPham().equals(productId));
        cart.setNgayCapNhat(java.time.LocalDateTime.now());
        cartRepository.save(cart);
        redisTemplate.opsForValue().set(CART_CACHE_PREFIX + userId, cart, 1, TimeUnit.HOURS);
    }

    public void clearCart(String userId) {
        Cart cart = getCartByUserId(userId);
        cart.getSanPham().clear();
        cart.setNgayCapNhat(java.time.LocalDateTime.now());
        cartRepository.save(cart);
        redisTemplate.opsForValue().set(CART_CACHE_PREFIX + userId, cart, 1, TimeUnit.HOURS);
    }
}