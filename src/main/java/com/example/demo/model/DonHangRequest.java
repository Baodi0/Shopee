package com.example.demo.model;

import java.util.List;
import java.util.UUID;

public class DonHangRequest {
    private UUID idDonHang;
    private String idNguoiDung;
    private String idShop;
    private Double tongGia;
    private String diaChiNhan;
    private String hinhThucThanhToan;
    private List<ChiTietDonHang> chiTietDonHang;
    private String TrangThai;

	public String getIdNguoiDung() {
		return idNguoiDung;
	}
	public void setIdNguoiDung(String idNguoiDung) {
		this.idNguoiDung = idNguoiDung;
	}
	public String getIdShop() {
		return idShop;
	}
	public void setIdShop(String idShop) {
		this.idShop = idShop;
	}
	public Double getTongGia() {
		return tongGia;
	}
	public void setTongGia(Double tongGia) {
		this.tongGia = tongGia;
	}
	public String getDiaChiNhan() {
		return diaChiNhan;
	}
	public void setDiaChiNhan(String diaChiNhan) {
		this.diaChiNhan = diaChiNhan;
	}
	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}
	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}
	public List<ChiTietDonHang> getChiTietDonHang() {
		return chiTietDonHang;
	}
	public void setChiTietDonHang(List<ChiTietDonHang> chiTietDonHang) {
		this.chiTietDonHang = chiTietDonHang;
	}
	public UUID getIdDonHang() {
		return idDonHang;
	}
	public void setIdDonHang(UUID idDonHang) {
		this.idDonHang = idDonHang;
	}
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

}
