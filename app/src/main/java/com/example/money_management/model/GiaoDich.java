package com.example.money_management.model;

import java.util.Date;

public class GiaoDich {
    private String maGD;
    private Date ngayGD;
    private String motaGD;
    private double tienGD;
    private String maKhoan;

    public GiaoDich(String maGD, Date ngayGD, String motaGD, double tienGD, String maKhoan) {
        this.maGD = maGD;
        this.ngayGD = ngayGD;
        this.motaGD = motaGD;
        this.tienGD = tienGD;
        this.maKhoan = maKhoan;
    }

    public GiaoDich(Date ngayGD, String motaGD, double tienGD, String maKhoan) {
        this.ngayGD = ngayGD;
        this.motaGD = motaGD;
        this.tienGD = tienGD;
        this.maKhoan = maKhoan;
    }

    public GiaoDich() {
    }

    public String getMaGD() {
        return maGD;
    }

    public void setMaGD(String maGD) {
        this.maGD = maGD;
    }

    public Date getNgayGD() {
        return ngayGD;
    }

    public void setNgayGD(Date ngayGD) {
        this.ngayGD = ngayGD;
    }

    public String getMotaGD() {
        return motaGD;
    }

    public void setMotaGD(String motaGD) {
        this.motaGD = motaGD;
    }

    public double getTienGD() {
        return tienGD;
    }

    public void setTienGD(double tienGD) {
        this.tienGD = tienGD;
    }

    public String getMaKhoan() {
        return maKhoan;
    }

    public void setMaKhoan(String maKhoan) {
        this.maKhoan = maKhoan;
    }
}
