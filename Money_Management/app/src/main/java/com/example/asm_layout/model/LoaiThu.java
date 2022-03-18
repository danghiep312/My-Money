package com.example.money_management.model;

public class LoaiThu extends Loai {
    public static final int LOAITHU=1;

    public LoaiThu(int idKhoan, String tenKhoan) {
        super(idKhoan, tenKhoan, LOAITHU);
    }

    public LoaiThu(String tenKhoan) {
        super(tenKhoan, LOAITHU);
    }

    public static int getLOAITHU() {
        return LOAITHU;
    }
}

