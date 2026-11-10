package com.example.quanlygiaodich.repository;

import com.example.quanlygiaodich.model.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {

    @Query("SELECT gd FROM GiaoDich gd JOIN gd.khachHang kh WHERE kh.tenKhachHang LIKE %:tenKhachHang%")
    List<GiaoDich> findByTenKhachHang(@Param("tenKhachHang") String tenKhachHang);

    @Query("SELECT gd FROM GiaoDich gd WHERE gd.loaiDichVu = :loaiDichVu")
    List<GiaoDich> findByLoaiDichVu(@Param("loaiDichVu") String loaiDichVu);

    @Query("SELECT gd FROM GiaoDich gd JOIN gd.khachHang kh WHERE kh.tenKhachHang LIKE %:tenKhachHang% AND gd.loaiDichVu = :loaiDichVu")
    List<GiaoDich> findByTenKhachHangAndLoaiDichVu(@Param("tenKhachHang") String tenKhachHang, @Param("loaiDichVu") String loaiDichVu);
}

