package com.example.money_management.model;

public class LoaiChi extends Loai{
    public static final int LOAICHI=0;

    public LoaiChi(int idKhoan, String tenKhoan) {
        super(idKhoan, tenKhoan, LOAICHI);
    }

    public LoaiChi(String tenKhoan) {
        super(tenKhoan, LOAICHI);
    }

    public static int getLOAICHI() {
        return LOAICHI;
    }
}

