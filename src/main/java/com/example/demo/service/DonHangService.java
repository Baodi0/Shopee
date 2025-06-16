package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChiTietDonHang;
import com.example.demo.model.DonHang;
import com.example.demo.model.DonHangKey;
import com.example.demo.model.DonHangRequest;
import com.example.demo.repository.ChiTietDonHangRepository;
import com.example.demo.repository.DonHangRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository donHangRepo;
    
    @Autowired
    ChiTietDonHangRepository chiTietDonHangRepo;


    @Autowired
    private ChiTietDonHangRepository chiTietRepo;

    public List<DonHang> getByUserId(String idNguoiDung) {
        return donHangRepo.findByIdNguoiDung(idNguoiDung);
    }

	public List<ChiTietDonHang> getById(UUID idDonHang) {
		return chiTietDonHangRepo.findByIdDonHang(idDonHang);

    }

	public DonHang createDonHang(DonHangRequest request) {
	    UUID id = UUID.randomUUID();
	    DonHangKey key = new DonHangKey(id, request.getIdNguoiDung());


	    DonHang donHang = new DonHang();
	    donHang.setKey(key);
	    donHang.setIdShop(request.getIdShop());
	    donHang.setNgayDat(LocalDateTime.now());
	    donHang.setTongGia(request.getTongGia());
	    donHang.setTrangThai("Đang xử lý"); 
	    donHang.setDiaChiNhan(request.getDiaChiNhan());
	    donHang.setHinhThucThanhToan(request.getHinhThucThanhToan());

	    donHangRepo.save(donHang);

	    if (request.getChiTietDonHang() != null) {
	        for (ChiTietDonHang ct : request.getChiTietDonHang()) {
	            ct.setIdDonHang(id);
	            chiTietRepo.save(ct);
	        }
	    }

	    return donHang;
	}


	public void updateTrangThai( UUID iddonhang, String idnguoidung, String trangthai) {
	    DonHangKey key = new DonHangKey(iddonhang, idnguoidung);
	    Optional<DonHang> optionalDonHang = donHangRepo.findById(key);
	    if (optionalDonHang.isPresent()) {
	        DonHang donHang = optionalDonHang.get();
	        donHang.setTrangThai(trangthai);
	        donHangRepo.save(donHang); 
	    }
	}


	public void delete(UUID iddondang, String iddguoidung) {
	    DonHangKey key = new DonHangKey(iddondang, iddguoidung);
	    donHangRepo.deleteById(key);
	}


}
