-- Tạo database
CREATE DATABASE IF NOT EXISTS quan_ly_giao_dich;
USE quan_ly_giao_dich;

-- Bảng khách hàng
CREATE TABLE IF NOT EXISTS khach_hang (
    ma_khach_hang BIGINT AUTO_INCREMENT PRIMARY KEY,
    ten_khach_hang VARCHAR(255) NOT NULL,
    so_dien_thoai VARCHAR(20),
    email VARCHAR(255)
);

-- Bảng giao dịch
CREATE TABLE IF NOT EXISTS giao_dich (
    ma_giao_dich VARCHAR(20) PRIMARY KEY,
    ma_khach_hang BIGINT NOT NULL,
    ngay_giao_dich VARCHAR(20) NOT NULL,
    loai_dich_vu VARCHAR(50) NOT NULL,
    don_gia DOUBLE NOT NULL,
    dien_tich DOUBLE NOT NULL,
    FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang(ma_khach_hang)
);

-- Dữ liệu mẫu khách hàng
INSERT INTO khach_hang (ten_khach_hang, so_dien_thoai, email) VALUES
('Nguyễn Văn An', '0901234567', 'an.nguyen@email.com'),
('Trần Thị Bình', '0912345678', 'binh.tran@email.com'),
('Lê Hoàng Cường', '0923456789', 'cuong.le@email.com'),
('Phạm Minh Đức', '0934567890', 'duc.pham@email.com'),
('Hoàng Thị Hoa', '0945678901', 'hoa.hoang@email.com');

-- Dữ liệu mẫu giao dịch
INSERT INTO giao_dich (ma_giao_dich, ma_khach_hang, ngay_giao_dich, loai_dich_vu, don_gia, dien_tich) VALUES
('MGD-0001', 1, '15/12/2026', 'Đất', 5000000, 100),
('MGD-0002', 2, '20/12/2026', 'Nhà và đất', 8000000, 150),
('MGD-0003', 3, '25/12/2026', 'Đất', 3000000, 200),
('MGD-0004', 4, '01/01/2027', 'Nhà và đất', 12000000, 80),
('MGD-0005', 5, '10/01/2027', 'Đất', 6000000, 120);

