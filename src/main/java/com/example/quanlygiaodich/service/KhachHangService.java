package com.example.quanlygiaodich.service;

import com.example.quanlygiaodich.model.KhachHang;
import com.example.quanlygiaodich.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    public Optional<KhachHang> findById(Long id) {
        return khachHangRepository.findById(id);
    }
}

