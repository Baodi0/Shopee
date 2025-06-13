package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    private String userId;
    private LocalDateTime ngayCapNhat;
    private List<CartItem> sanPham;

    public Cart() {
        this.sanPham = new ArrayList<>();
        this.ngayCapNhat = LocalDateTime.now();
    }

    public Cart(String userId) {
        this.userId = userId;
        this.sanPham = new ArrayList<>();
        this.ngayCapNhat = LocalDateTime.now();
    }

    public static class CartItem {
        private String idSanPham;
        private Integer soLuong;
        private Double giaLucThem;

        public CartItem() {}

        public CartItem(String idSanPham, Integer soLuong, Double giaLucThem) {
            this.idSanPham = idSanPham;
            this.soLuong = soLuong;
            this.giaLucThem = giaLucThem;
        }

        // Getters and Setters
        public String getIdSanPham() { return idSanPham; }
        public void setIdSanPham(String idSanPham) { this.idSanPham = idSanPham; }

        public Integer getSoLuong() { return soLuong; }
        public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }

        public Double getGiaLucThem() { return giaLucThem; }
        public void setGiaLucThem(Double giaLucThem) { this.giaLucThem = giaLucThem; }
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }
    public void setNgayCapNhat(LocalDateTime ngayCapNhat) { this.ngayCapNhat = ngayCapNhat; }

    public List<CartItem> getSanPham() { return sanPham; }
    public void setSanPham(List<CartItem> sanPham) { this.sanPham = sanPham; }
}