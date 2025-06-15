package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;

import java.util.List;
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
        String user_id = userId.replaceAll("[{}]", "");

        if (cachedCart != null) {
            return cachedCart;
        }

        List<Cart> cartList = cartRepository.findAll();
        Cart mergedCart = new Cart(user_id); 

        for (Cart cart : cartList) {
            if (cart.getUserId() != null && cart.getUserId().equals(user_id)) {
                mergedCart.addSanPham(cart.getSanPham());
            }
           

        }

        if (!mergedCart.getSanPham().isEmpty()) {
            redisTemplate.opsForValue().set(cacheKey, mergedCart, 1, TimeUnit.HOURS);
        }

        return mergedCart;
    }

    public Cart addToCart(String userId, String productId, Integer quantity) {
    	productId = productId.replaceAll("[{}]", "");
    	userId = userId.replaceAll("[{}]", "");
    	
    	
    	Cart cart = getCartByUserId(userId);
    	if (cart == null) {
    	    cart = new Cart(userId);
    	}      



        boolean found = false;
        for (Cart.CartItem item : cart.getSanPham()) {
            if (item.getIdSanPham().equals(productId)) {
                item.setSoLuong(quantity);
                found = true;
                break;
            }
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