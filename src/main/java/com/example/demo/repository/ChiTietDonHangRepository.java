package com.example.demo.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ChiTietDonHang;

@Repository
public interface ChiTietDonHangRepository extends CassandraRepository<ChiTietDonHang, UUID> {
    List<ChiTietDonHang> findByIdDonHang(UUID idDonHang);
    
}