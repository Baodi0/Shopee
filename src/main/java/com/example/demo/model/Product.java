package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Map;

@Document(collection = "products")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "products") 
public class Product {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String tenSanPham;
    @Field(type = FieldType.Text)
    private String moTa;
    @Field(type = FieldType.Keyword) 
    private String danhMuc;
    private double gia;
    private int soLuong; 
    private String shopID;
    private double diemDanhGia;
    private List<String> hinhAnh;
    private Map<String, List<String>> thuocTinh; 
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getShopID() {
		return shopID;
	}
	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	public double getDiemDanhGia() {
		return diemDanhGia;
	}
	public void setDiemDanhGia(double diemDanhGia) {
		this.diemDanhGia = diemDanhGia;
	}
	public List<String> getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(List<String> hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public Map<String, List<String>> getThuocTinh() {
		return thuocTinh;
	}
	public void setThuocTinh(Map<String, List<String>> thuocTinh) {
		this.thuocTinh = thuocTinh;
	}

}