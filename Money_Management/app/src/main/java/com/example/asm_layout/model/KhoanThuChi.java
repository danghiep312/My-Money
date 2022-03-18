package com.example.money_management.model;

import java.io.Serializable;

public class KhoanThuChi implements Serializable {
    public int idKhoan;
    public int idGiaoDich;
    public String ngayGiaoDich;
    public String noiDung;
    public double soTien;
    public int loaiKhoan;

    public KhoanThuChi(int idKhoan, int idGiaoDich, String ngayGiaoDich, String noiDung, double soTien, int loaiKhoan) {
        this.idKhoan = idKhoan;
        this.idGiaoDich = idGiaoDich;
        this.ngayGiaoDich = ngayGiaoDich;
        this.noiDung = noiDung;
        this.soTien = soTien;
        this.loaiKhoan = loaiKhoan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public KhoanThuChi(int idKhoan, String ngayGiaoDich, double soTien, int loaiKhoan) {
        this.idKhoan = idKhoan;
        this.ngayGiaoDich = ngayGiaoDich;
        this.soTien = soTien;
        this.loaiKhoan = loaiKhoan;
    }

    public int getIdKhoan() {
        return idKhoan;
    }

    public void setIdKhoan(int idKhoan) {
        this.idKhoan = idKhoan;
    }

    public int getIdGiaoDich() {
        return idGiaoDich;
    }

    public void setIdGiaoDich(int idGiaoDich) {
        this.idGiaoDich = idGiaoDich;
    }

    public String getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(String ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public int getLoaiKhoan() {
        return loaiKhoan;
    }

    public void setLoaiKhoan(int loaiKhoan) {
        this.loaiKhoan = loaiKhoan;
    }
}
