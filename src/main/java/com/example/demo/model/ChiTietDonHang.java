package com.example.demo.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;
import java.util.UUID;

@Table("chitietdonhang")
public class ChiTietDonHang implements Serializable{

	private static final long serialVersionUID = 1L;

    @PrimaryKeyColumn(name = "iddonhang", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID idDonHang;
    
    @Column("soluong")
    private Integer soLuong;

    @Column("idsanpham")
    private String idSanPham;

    @Column("giadonvi")
    private Double giaDonVi;
    

    // Getters & Setters
    public UUID getIdDonHang() { return idDonHang; }
    public void setIdDonHang(UUID idDonHang) {this.idDonHang =  idDonHang;}

    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }

    public String getIdSanPham() { return idSanPham; }
    public void setIdSanPham(String idSanPham) { this.idSanPham = idSanPham; }
    
    public Double getGiaDonVi() { return giaDonVi; }
    public void setGiaDonVi(Double giaDonVi) { this.giaDonVi = giaDonVi; }
}
