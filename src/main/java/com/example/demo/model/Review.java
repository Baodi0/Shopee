package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "DanhGia")
public class Review implements Serializable{

	private static final long serialVersionUID = 1L;
    @Id
    private String _id;
    
    @NotNull(message = "User ID không được để trống")
    private String userID;
    
    @NotNull(message = "Sản phẩm ID không được để trống")
    private String sanPhamID;
    
    @NotNull(message = "Điểm đánh giá không được để trống")
    @Min(value = 1, message = "Điểm đánh giá tối thiểu là 1")
    @Max(value = 5, message = "Điểm đánh giá tối đa là 5")
    private Integer diem;
    
    private String binhLuan;
    private List<String> hinhAnh;
    private List<String> video;
    private LocalDateTime thoiGian;

    public Review() {
        this.thoiGian = LocalDateTime.now();
    }

    public Review(String _id,String userId, String sanPhamId, Integer diem, String binhLuan, List<String> hinhAnh, List<String> video) {
    	this._id = _id;
        this.userID = userId;
        this.sanPhamID = sanPhamId;
        this.diem = diem;
        this.binhLuan = binhLuan;
        this.hinhAnh = hinhAnh;
        this.video = video;
        this.thoiGian = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() { return _id; }
    public void setId(String id) { this._id = id; }

    public String getUserId() { return userID; }
    public void setUserId(String userId) { this.userID = userId; }

    public String getSanPhamId() { return sanPhamID; }
    public void setSanPhamId(String sanPhamId) { this.sanPhamID = sanPhamId; }

    public Integer getDiem() { return diem; }
    public void setDiem(Integer diem) { this.diem = diem; }

    public String getBinhLuan() { return binhLuan; }
    public void setBinhLuan(String binhLuan) { this.binhLuan = binhLuan; }

    public List<String> getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(List<String> hinhAnh) { this.hinhAnh = hinhAnh; }

    public List<String> getVideo() { return video; }
    public void setVideo(List<String> video) { this.video = video; }

    public LocalDateTime getThoiGian() { return thoiGian; }
    public void setThoiGian(LocalDateTime thoiGian) { this.thoiGian = thoiGian; }
}