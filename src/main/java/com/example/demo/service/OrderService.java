package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String ORDER_CACHE_PREFIX = "order:";

    public List<Order> getAllOrdersByUserId(String userId) {
        String cacheKey = ORDER_CACHE_PREFIX + userId;
        @SuppressWarnings("unchecked")
		List<Order> cachedOrders = (List<Order>) redisTemplate.opsForValue().get(cacheKey);

        if (cachedOrders != null) {
            return cachedOrders;
        }

        List<Order> orders = orderRepository.findByUserId(userId);
        redisTemplate.opsForValue().set(cacheKey, orders, 1, TimeUnit.HOURS);
        return orders;
    }

    
    public Optional<Order> getOrderById(String id) {
        String cacheKey = ORDER_CACHE_PREFIX + id;
        Order cachedOrder = (Order) redisTemplate.opsForValue().get(cacheKey);

        if (cachedOrder != null) {
            return Optional.of(cachedOrder);
        }

        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(o -> redisTemplate.opsForValue().set(cacheKey, o, 1, TimeUnit.HOURS));

        return order;
    }

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        redisTemplate.opsForValue().set(ORDER_CACHE_PREFIX + savedOrder.getId(), savedOrder, 1, TimeUnit.HOURS);
        return savedOrder;
    }

    public void updateOrderStatus(String id, String newStatus) {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new RuntimeException("Đơn hàng không tồn tại");
        }

        Order updatedOrder = order.get();
        updatedOrder.setTrangThai(newStatus);
        orderRepository.save(updatedOrder);
        redisTemplate.opsForValue().set(ORDER_CACHE_PREFIX + id, updatedOrder, 1, TimeUnit.HOURS);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
        redisTemplate.delete(ORDER_CACHE_PREFIX + id);
    }
}