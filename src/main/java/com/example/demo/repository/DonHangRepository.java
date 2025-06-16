package com.example.demo.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DonHang;
import com.example.demo.model.DonHangKey;

import java.util.List;
import java.util.UUID;

@Repository
public interface DonHangRepository extends CassandraRepository<DonHang, DonHangKey> {
    List<DonHang> findByIdNguoiDung(DonHangKey key);
}



