package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.List;

@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String userID;
    private String sanPhamID;
    private int diem;
    private String binhLuan;
    private List<String> hinhAnh;
    private List<String> video;
    private Instant thoiGian;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getSanPhamID() {
		return sanPhamID;
	}
	public void setSanPhamID(String sanPhamID) {
		this.sanPhamID = sanPhamID;
	}
	public int getDiem() {
		return diem;
	}
	public void setDiem(int diem) {
		this.diem = diem;
	}
	public String getBinhLuan() {
		return binhLuan;
	}
	public void setBinhLuan(String binhLuan) {
		this.binhLuan = binhLuan;
	}
	public List<String> getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(List<String> hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public List<String> getVideo() {
		return video;
	}
	public void setVideo(List<String> video) {
		this.video = video;
	}
	public Instant getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Instant thoiGian) {
		this.thoiGian = thoiGian;
	}

}