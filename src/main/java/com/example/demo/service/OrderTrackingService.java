package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repository.cassandra.OrderDetailRepository;
import com.example.demo.repository.cassandra.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;

@Service
public class OrderTrackingService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final RedisTemplate<String, Order> redisTemplate; // Redis cho cache trạng thái đơn hàng

    @Autowired
    public OrderTrackingService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, RedisTemplate<String, Order> redisTemplate) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.redisTemplate = redisTemplate;
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByIdNguoiDung(userId);
    }

    public Order getOrderDetails(String userId, UUID orderId) {
        Order order = redisTemplate.opsForValue().get("order:" + orderId);
        if (order != null && order.getIdNguoiDung().equals(userId)) {
            return order;
        }

        order = orderRepository.findById(orderId).orElse(null);
        if (order != null && order.getIdNguoiDung().equals(userId)) {
            redisTemplate.opsForValue().set("order:" + orderId, order, 5, TimeUnit.MINUTES); // Cache 5 phút
            return order;
        }
        return null;
    }

    public Order updateOrderStatus(UUID orderId, String newStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setTrangThai(newStatus);
            Order updatedOrder = orderRepository.save(order);
            redisTemplate.delete("order:" + orderId); 
            return updatedOrder;
        }
        return null;
    }
}