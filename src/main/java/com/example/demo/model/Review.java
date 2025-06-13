package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    
    @NotNull(message = "User ID không được để trống")
    private String userId;
    
    @NotNull(message = "Sản phẩm ID không được để trống")
    private String sanPhamId;
    
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

    public Review(String userId, String sanPhamId, Integer diem, String binhLuan) {
        this.userId = userId;
        this.sanPhamId = sanPhamId;
        this.diem = diem;
        this.binhLuan = binhLuan;
        this.thoiGian = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSanPhamId() { return sanPhamId; }
    public void setSanPhamId(String sanPhamId) { this.sanPhamId = sanPhamId; }

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