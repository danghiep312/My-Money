package com.example.money_management.model;

public class Khoan {
    private String maKhoan;
    private String tenKhoan;
    private String loaiKhoan;

    public Khoan(String maKhoan, String tenKhoan, String loaiKhoan) {
        this.maKhoan = maKhoan;
        this.tenKhoan = tenKhoan;
        this.loaiKhoan = loaiKhoan;
    }

    public Khoan(String tenKhoan, String loaiKhoan) {
        this.tenKhoan = tenKhoan;
        this.loaiKhoan = loaiKhoan;
    }

    public Khoan() {
    }

    public String getMaKhoan() {
        return maKhoan;
    }

    public void setMaKhoan(String maKhoan) {
        this.maKhoan = maKhoan;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public String getLoaiKhoan() {
        return loaiKhoan;
    }

    public void setLoaiKhoan(String loaiKhoan) {
        this.loaiKhoan = loaiKhoan;
    }
}
