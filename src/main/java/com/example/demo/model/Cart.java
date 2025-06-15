package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Document(collection = "GioHang")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String userID;
    private LocalDateTime ngayCapNhat;
    private List<CartItem> sanPham;

    public Cart() {
        this.sanPham = new ArrayList<>();
        this.ngayCapNhat = LocalDateTime.now();
    }

    public Cart(String userId) {
        this.userID = userId;
        this.sanPham = new ArrayList<>();
        this.ngayCapNhat = LocalDateTime.now();
    }

    public static class CartItem implements Serializable {
        private static final long serialVersionUID = 1L;
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

    public String getUserId() { return userID; }
    public void setUserId(String userId) { this.userID = userId; }

    public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }
    public void setNgayCapNhat(LocalDateTime ngayCapNhat) { this.ngayCapNhat = ngayCapNhat; }

    public List<CartItem> getSanPham() { return sanPham; }
    public void setSanPham(List<CartItem> sanPham) { this.sanPham = sanPham; }
    
    public CartItem GetSanPhamById(String idSanPham) {
    	for(CartItem item : this.sanPham) {
    		if(item.getIdSanPham().equals(idSanPham)) {
    			return item;
    		}
    	}
    	return null;
    }
    
    public void addSanPham(List<CartItem> items) {
        for (CartItem item : items) {
	    	if (sanPham == null) {
	            sanPham = new ArrayList<>();
	        }
	        sanPham.add(item);

        }
    }

}