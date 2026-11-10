package com.example.quanlygiaodich.dto;

import jakarta.validation.constraints.*;

public class GiaoDichDTO {

    @NotBlank(message = "Mã giao dịch không được để trống")
    @Pattern(regexp = "^MGD-\\d{4}$", message = "Mã giao dịch phải có định dạng MGD-XXXX (X là chữ số 0-9)")
    private String maGiaoDich;

    @NotNull(message = "Khách hàng không được để trống")
    private Long maKhachHang;

    @NotBlank(message = "Loại dịch vụ không được để trống")
    private String loaiDichVu;

    @NotBlank(message = "Ngày giao dịch không được để trống")
    private String ngayGiaoDich;

    @NotNull(message = "Đơn giá không được để trống")
    @DecimalMin(value = "500001", message = "Đơn giá phải lớn hơn 500.000 VND")
    private Double donGia;

    @NotNull(message = "Diện tích không được để trống")
    @DecimalMin(value = "20.01", message = "Diện tích phải lớn hơn 20 m²")
    private Double dienTich;

    public GiaoDichDTO() {
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public Long getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(Long maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public String getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(String ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
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

