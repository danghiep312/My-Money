package com.example.money_management.model;

public class KhoanThu extends KhoanThuChi {
    public static final int LOAIKHOAN=1;

    public KhoanThu(int idGiaoDich, int idKhoan, String ngayGiaoDich, String noiDung, double soTien) {
        super(idGiaoDich, idKhoan, ngayGiaoDich, noiDung, soTien, LOAIKHOAN);
    }

    public KhoanThu(int idKhoan, String ngayGiaoDich, double soTien) {
        super(idKhoan, ngayGiaoDich, soTien, LOAIKHOAN);
    }

    public static int getLOAIKHOAN() {
        return LOAIKHOAN;
    }
}