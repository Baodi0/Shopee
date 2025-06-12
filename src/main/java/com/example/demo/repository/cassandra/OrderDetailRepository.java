package com.example.demo.repository.cassandra;

import com.example.demo.model.OrderDetail;
import com.example.demo.model.OrderDetailKey; // Import the new key class
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends CassandraRepository<OrderDetail, OrderDetailKey> {
    List<OrderDetail> findByKey_IdDonHang(UUID idDonHang);
}