package com.example.quanlygiaodich.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "giao_dich")
public class GiaoDich {

    @Id
    @Column(name = "ma_giao_dich", nullable = false)
    private String maGiaoDich;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_khach_hang", nullable = false)
    private KhachHang khachHang;

    @Column(name = "ngay_giao_dich", nullable = false)
    private String ngayGiaoDich;

    @Column(name = "loai_dich_vu", nullable = false)
    private String loaiDichVu;

    @Column(name = "don_gia", nullable = false)
    private Double donGia;

    @Column(name = "dien_tich", nullable = false)
    private Double dienTich;

    public GiaoDich() {
    }

    public GiaoDich(String maGiaoDich, KhachHang khachHang, String ngayGiaoDich, String loaiDichVu, Double donGia, Double dienTich) {
        this.maGiaoDich = maGiaoDich;
        this.khachHang = khachHang;
        this.ngayGiaoDich = ngayGiaoDich;
        this.loaiDichVu = loaiDichVu;
        this.donGia = donGia;
        this.dienTich = dienTich;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(String ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public String getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }
}

