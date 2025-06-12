package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "carts")
public class Cart implements Serializable { 
    @Id
    private String id;
    private String userID;
    private LocalDateTime ngayCapNhat;
    private List<CartItem> sanPham;

    public static class CartItem implements Serializable { 
        private String idSanPham;
        private int soLuong;
        private double giaLucThem;
		public String getIdSanPham() {
			return idSanPham;
		}
		public void setIdSanPham(String idSanPham) {
			this.idSanPham = idSanPham;
		}
		public int getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
		public double getGiaLucThem() {
			return giaLucThem;
		}
		public void setGiaLucThem(double giaLucThem) {
			this.giaLucThem = giaLucThem;
		} 
    }    // Getters and Setters

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public LocalDateTime getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(LocalDateTime ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public List<CartItem> getSanPham() {
		return sanPham;
	}

	public void setSanPham(List<CartItem> sanPham) {
		this.sanPham = sanPham;
	}
}