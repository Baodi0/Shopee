package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private String shopId;
    private LocalDateTime ngayDat;
    private Double tongGia;
    private String trangThai;
    private String diaChiNhan;
    private String hinhThucThanhToan;
    private List<OrderItem> chiTietDonHang;

    public Order() {
        this.ngayDat = LocalDateTime.now();
        this.trangThai = "CHO_XAC_NHAN";
    }

    public static class OrderItem {
        private String idSanPham;
        private Integer soLuong;
        private Double giaDonVi;

        public OrderItem() {}

        public OrderItem(String idSanPham, Integer soLuong, Double giaDonVi) {
            this.idSanPham = idSanPham;
            this.soLuong = soLuong;
            this.giaDonVi = giaDonVi;
        }

        // Getters and Setters
        public String getIdSanPham() { return idSanPham; }
        public void setIdSanPham(String idSanPham) { this.idSanPham = idSanPham; }

        public Integer getSoLuong() { return soLuong; }
        public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }

        public Double getGiaDonVi() { return giaDonVi; }
        public void setGiaDonVi(Double giaDonVi) { this.giaDonVi = giaDonVi; }
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getShopId() { return shopId; }
    public void setShopId(String shopId) { this.shopId = shopId; }

    public LocalDateTime getNgayDat() { return ngayDat; }
    public void setNgayDat(LocalDateTime ngayDat) { this.ngayDat = ngayDat; }

    public Double getTongGia() { return tongGia; }
    public void setTongGia(Double tongGia) { this.tongGia = tongGia; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getDiaChiNhan() { return diaChiNhan; }
    public void setDiaChiNhan(String diaChiNhan) { this.diaChiNhan = diaChiNhan; }

    public String getHinhThucThanhToan() { return hinhThucThanhToan; }
    public void setHinhThucThanhToan(String hinhThucThanhToan) { this.hinhThucThanhToan = hinhThucThanhToan; }

    public List<OrderItem> getChiTietDonHang() { return chiTietDonHang; }
    public void setChiTietDonHang(List<OrderItem> chiTietDonHang) { this.chiTietDonHang = chiTietDonHang; }
}
