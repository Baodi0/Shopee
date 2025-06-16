package com.example.demo.controller;

import com.example.demo.model.ChiTietDonHang;
import com.example.demo.model.DonHang;
import com.example.demo.model.DonHangRequest;
import com.example.demo.service.DonHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    @GetMapping("/user/{idNguoiDung}")
    public List<DonHang> getByUserId(@PathVariable String idNguoiDung) {
        return donHangService.getByUserId(idNguoiDung);
    }

    @GetMapping("/{idDonHang}")
    public List<ChiTietDonHang> getById(@PathVariable UUID idDonHang) {
        return donHangService.getById(idDonHang);
    }

    @PostMapping
    public DonHang create(@RequestBody DonHangRequest request) {
        return donHangService.createDonHang(request);
    }

    @PutMapping
    public void updateStatus(@RequestParam UUID iddonhang, String idnguoidung, String trangthai ) {
        donHangService.updateTrangThai(iddonhang, idnguoidung, trangthai);
    }

    @DeleteMapping("/{idDonHang}")
    public void delete(@PathVariable UUID iddonhang, String idnguoidung) {
        donHangService.delete(iddonhang, idnguoidung);
    }
}
