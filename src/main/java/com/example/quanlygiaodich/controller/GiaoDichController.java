package com.example.quanlygiaodich.controller;

import com.example.quanlygiaodich.dto.GiaoDichDTO;
import com.example.quanlygiaodich.model.GiaoDich;
import com.example.quanlygiaodich.model.KhachHang;
import com.example.quanlygiaodich.service.GiaoDichService;
import com.example.quanlygiaodich.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/giao-dich")
public class GiaoDichController {

    @Autowired
    private GiaoDichService giaoDichService;

    @Autowired
    private KhachHangService khachHangService;

    /**
     * Hiển thị danh sách giao dịch
     */
    @GetMapping
    public String danhSachGiaoDich(Model model) {
        List<GiaoDich> danhSach = giaoDichService.findAll();
        model.addAttribute("danhSachGiaoDich", danhSach);
        return "danh-sach";
    }

    /**
     * Hiển thị form thêm mới giao dịch
     */
    @GetMapping("/them-moi")
    public String formThemMoi(Model model) {
        model.addAttribute("giaoDichDTO", new GiaoDichDTO());
        model.addAttribute("danhSachKhachHang", khachHangService.findAll());
        return "them-moi";
    }

    /**
     * Xử lý thêm mới giao dịch
     */
    @PostMapping("/them-moi")
    public String themMoiGiaoDich(@Valid @ModelAttribute("giaoDichDTO") GiaoDichDTO giaoDichDTO,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        // Validate ngày giao dịch phải lớn hơn thời gian hiện tại
        if (giaoDichDTO.getNgayGiaoDich() != null && !giaoDichDTO.getNgayGiaoDich().trim().isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate ngayGD = LocalDate.parse(giaoDichDTO.getNgayGiaoDich(), formatter);
                if (!ngayGD.isAfter(LocalDate.now())) {
                    bindingResult.rejectValue("ngayGiaoDich", "error.ngayGiaoDich",
                            "Ngày giao dịch phải lớn hơn thời gian hiện tại");
                }
            } catch (DateTimeParseException e) {
                bindingResult.rejectValue("ngayGiaoDich", "error.ngayGiaoDich",
                        "Ngày giao dịch phải có định dạng dd/MM/yyyy");
            }
        }

        // Kiểm tra mã giao dịch đã tồn tại
        if (giaoDichDTO.getMaGiaoDich() != null && !giaoDichDTO.getMaGiaoDich().trim().isEmpty()) {
            Optional<GiaoDich> existing = giaoDichService.findById(giaoDichDTO.getMaGiaoDich());
            if (existing.isPresent()) {
                bindingResult.rejectValue("maGiaoDich", "error.maGiaoDich",
                        "Mã giao dịch đã tồn tại");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("danhSachKhachHang", khachHangService.findAll());
            return "them-moi";
        }

        // Lưu giao dịch
        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setMaGiaoDich(giaoDichDTO.getMaGiaoDich());

        Optional<KhachHang> khachHang = khachHangService.findById(giaoDichDTO.getMaKhachHang());
        if (khachHang.isPresent()) {
            giaoDich.setKhachHang(khachHang.get());
        }

        giaoDich.setLoaiDichVu(giaoDichDTO.getLoaiDichVu());
        giaoDich.setNgayGiaoDich(giaoDichDTO.getNgayGiaoDich());
        giaoDich.setDonGia(giaoDichDTO.getDonGia());
        giaoDich.setDienTich(giaoDichDTO.getDienTich());

        giaoDichService.save(giaoDich);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm mới giao dịch thành công!");
        return "redirect:/giao-dich";
    }

    /**
     * Xem chi tiết giao dịch
     */
    @GetMapping("/chi-tiet/{id}")
    public String chiTietGiaoDich(@PathVariable("id") String id, Model model) {
        Optional<GiaoDich> giaoDich = giaoDichService.findById(id);
        if (giaoDich.isPresent()) {
            model.addAttribute("giaoDich", giaoDich.get());
            return "chi-tiet";
        }
        return "redirect:/giao-dich";
    }

    /**
     * Xóa giao dịch
     */
    @GetMapping("/xoa/{id}")
    public String xoaGiaoDich(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        giaoDichService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa giao dịch thành công!");
        return "redirect:/giao-dich";
    }

    /**
     * Tìm kiếm giao dịch
     */
    @GetMapping("/tim-kiem")
    public String timKiemGiaoDich(@RequestParam(value = "tenKhachHang", required = false) String tenKhachHang,
                                   @RequestParam(value = "loaiDichVu", required = false) String loaiDichVu,
                                   Model model) {
        List<GiaoDich> ketQua = giaoDichService.search(tenKhachHang, loaiDichVu);
        model.addAttribute("danhSachGiaoDich", ketQua);
        model.addAttribute("tenKhachHang", tenKhachHang);
        model.addAttribute("loaiDichVu", loaiDichVu);
        return "danh-sach";
    }
}

