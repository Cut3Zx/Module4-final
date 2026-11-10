package com.example.quanlygiaodich.service;

import com.example.quanlygiaodich.model.GiaoDich;
import com.example.quanlygiaodich.repository.GiaoDichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiaoDichService {

    @Autowired
    private GiaoDichRepository giaoDichRepository;

    public List<GiaoDich> findAll() {
        return giaoDichRepository.findAll();
    }

    public Optional<GiaoDich> findById(String id) {
        return giaoDichRepository.findById(id);
    }

    public GiaoDich save(GiaoDich giaoDich) {
        return giaoDichRepository.save(giaoDich);
    }

    public void deleteById(String id) {
        giaoDichRepository.deleteById(id);
    }

    public List<GiaoDich> search(String tenKhachHang, String loaiDichVu) {
        if (tenKhachHang != null && !tenKhachHang.trim().isEmpty()
                && loaiDichVu != null && !loaiDichVu.trim().isEmpty()) {
            return giaoDichRepository.findByTenKhachHangAndLoaiDichVu(tenKhachHang.trim(), loaiDichVu.trim());
        } else if (tenKhachHang != null && !tenKhachHang.trim().isEmpty()) {
            return giaoDichRepository.findByTenKhachHang(tenKhachHang.trim());
        } else if (loaiDichVu != null && !loaiDichVu.trim().isEmpty()) {
            return giaoDichRepository.findByLoaiDichVu(loaiDichVu.trim());
        }
        return giaoDichRepository.findAll();
    }
}

