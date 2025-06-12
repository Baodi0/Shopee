package com.example.demo.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@PrimaryKeyClass
public class OrderDetailKey implements Serializable {

    @PrimaryKeyColumn(name = "idDonHang", type = PrimaryKeyType.PARTITIONED)
    private UUID idDonHang;

    @PrimaryKeyColumn(name = "idSanPham", type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private String idSanPham;

    // Constructors
    public OrderDetailKey() {}

    public OrderDetailKey(UUID idDonHang, String idSanPham) {
        this.idDonHang = idDonHang;
        this.idSanPham = idSanPham;
    }

    // Getters and Setters
    public UUID getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(UUID idDonHang) {
        this.idDonHang = idDonHang;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    // hashCode and equals are crucial for composite primary keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailKey that = (OrderDetailKey) o;
        return Objects.equals(idDonHang, that.idDonHang) &&
               Objects.equals(idSanPham, that.idSanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDonHang, idSanPham);
    }
}