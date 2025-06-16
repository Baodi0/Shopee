package com.example.demo.model;

import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table("donhang")
public class DonHang implements Serializable{

	private static final long serialVersionUID = 1L;

    @PrimaryKey
    private DonHangKey key;
    
    @Column("idshop")
    private String idShop;

    @Column("ngaydat")
    private LocalDateTime ngayDat;

    @Column("tonggia")
    private Double tongGia;

    @Column("trangthai")
    private String trangThai;

    @Column("diachinhan")
    private String diaChiNhan;

    @Column("hinhthucthanhtoan")
    private String hinhThucThanhToan;

    // Getters & Setters
    
    public DonHangKey getIdKey() {return key;}
    public void setKey(DonHangKey key) {this.key = key;}
    

    public String getIdShop() { return idShop; }
    public void setIdShop(String idShop) { this.idShop = idShop; }

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
}
