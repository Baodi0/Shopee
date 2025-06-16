package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ReviewRequest {
	private String _id;
    
    private String userID;
    
    private String sanPhamID;
    
    private Integer diem;
    
    private String binhLuan;
    private List<String> hinhAnh;
    private List<String> video;
    private LocalDateTime thoiGian;
    // Constructor
    public ReviewRequest() {}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
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
	public Integer getDiem() {
		return diem;
	}
	public void setDiem(Integer diem) {
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
	public LocalDateTime getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(LocalDateTime thoiGian) {
		this.thoiGian = thoiGian;
	}
	
}