package com.example.demo.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Table("donhang")
public class Order implements Serializable {
    @PrimaryKeyColumn(name = "idNguoiDung", type = PrimaryKeyType.PARTITIONED)
    private String idNguoiDung;
    @PrimaryKeyColumn(name = "idDonHang", type = PrimaryKeyType.CLUSTERED)
    private UUID idDonHang; 
    private Instant ngayDat;
    private double tongGia;
    private String trangThai; 
    private String diaChiNhan;
    private String idShop;
    private String hinhThucThanhToan;
	public Instant getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Instant ngayDat) {
		this.ngayDat = ngayDat;
	}
	public double getTongGia() {
		return tongGia;
	}
	public void setTongGia(double tongGia) {
		this.tongGia = tongGia;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getDiaChiNhan() {
		return diaChiNhan;
	}
	public void setDiaChiNhan(String diaChiNhan) {
		this.diaChiNhan = diaChiNhan;
	}
	public String getIdShop() {
		return idShop;
	}
	public void setIdShop(String idShop) {
		this.idShop = idShop;
	}
	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}
	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}
	public Object getIdNguoiDung() {
		return idNguoiDung;
	}

}