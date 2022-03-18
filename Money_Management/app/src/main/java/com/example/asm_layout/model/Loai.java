package com.example.money_management.model;

import java.io.Serializable;

public class Loai implements Serializable {
    public int idKhoan;
    public String tenKhoan;
    public int loaiKhoan;

    public Loai(int idKhoan, String tenKhoan, int loaiKhoan) {
        this.idKhoan = idKhoan;
        this.tenKhoan = tenKhoan;
        this.loaiKhoan = loaiKhoan;
    }

    public Loai(String tenKhoan, int loaiKhoan) {
        this.tenKhoan = tenKhoan;
        this.loaiKhoan = loaiKhoan;
    }

    public int getIdKhoan() {
        return idKhoan;
    }

    public void setIdKhoan(int idKhoan) {
        this.idKhoan = idKhoan;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public int getLoaiKhoan() {
        return loaiKhoan;
    }

    public void setLoaiKhoan(int loaiKhoan) {
        this.loaiKhoan = loaiKhoan;
    }


}
