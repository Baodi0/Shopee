package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.data.mongodb.core.index.TextIndexed;

import java.util.List;
import java.util.Map;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @TextIndexed
    private String tenSanPham;
    
    @TextIndexed
    private String moTa;
    
    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải lớn hơn 0")
    private Double gia;
    
    @NotNull(message = "Số lượng không được để trống")
    private Integer soLuong;
    
    private List<String> hinhAnh;
    
    @TextIndexed
    private String danhMuc;
    
    private String shopId;
    private Double diemDanhGia;
    private Map<String, Object> thuocTinh;

    // Constructors
    public Product() {}

    public Product(String tenSanPham, String moTa, Double gia, Integer soLuong, 
                  String danhMuc, String shopId) {
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.gia = gia;
        this.soLuong = soLuong;
        this.danhMuc = danhMuc;
        this.shopId = shopId;
        this.diemDanhGia = 0.0;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public Double getGia() { return gia; }
    public void setGia(Double gia) { this.gia = gia; }

    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }

    public List<String> getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(List<String> hinhAnh) { this.hinhAnh = hinhAnh; }

    public String getDanhMuc() { return danhMuc; }
    public void setDanhMuc(String danhMuc) { this.danhMuc = danhMuc; }

    public String getShopId() { return shopId; }
    public void setShopId(String shopId) { this.shopId = shopId; }

    public Double getDiemDanhGia() { return diemDanhGia; }
    public void setDiemDanhGia(Double diemDanhGia) { this.diemDanhGia = diemDanhGia; }

    public Map<String, Object> getThuocTinh() { return thuocTinh; }
    public void setThuocTinh(Map<String, Object> thuocTinh) { this.thuocTinh = thuocTinh; }
}