package com.example.money_management.model;

public class KhoanChi extends KhoanThuChi{
    public static final int LOAIKHOAN=0;

    public KhoanChi(int idGiaoDich, int idKhoan, String ngayGiaoDich, String noiDung, double soTien) {
        super(idGiaoDich, idKhoan, ngayGiaoDich, noiDung, soTien, LOAIKHOAN);
    }

    public KhoanChi(int idKhoan, String ngayGiaoDich, double soTien) {
        super(idKhoan, ngayGiaoDich, soTien, LOAIKHOAN);
    }

    public static int getLOAIKHOAN() {
        return LOAIKHOAN;
    }
}
