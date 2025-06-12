package com.example.demo.repository.cassandra;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CassandraRepository<Order, UUID> {
    List<Order> findByIdNguoiDung(String idNguoiDung);
}