package com.example.demo.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

// No need for nested class definition here anymore

@Table("chitietdonhang")
public class OrderDetail implements Serializable{

    @PrimaryKey
    private OrderDetailKey key; // Reference to the separate key class

    @Column("soLuong") // Explicitly map column name if different from field name
    private int soLuong;

    @Column("giaDonVi") // Explicitly map column name if different from field name
    private double giaDonVi;

    // Getters and Setters
    public OrderDetailKey getKey() {
        return key;
    }

    public void setKey(OrderDetailKey key) {
        this.key = key;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaDonVi() {
        return giaDonVi;
    }

    public void setGiaDonVi(double giaDonVi) {
        this.giaDonVi = giaDonVi;
    }
}