package com.example.money_management.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.money_management.model.Khoan;

import java.util.ArrayList;
import java.util.List;

public class KhoanDAO {
    private SQLiteDatabase db;

    public KhoanDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Khoan khoan) {
        ContentValues values = new ContentValues();
        values.put("tenKhoan", khoan.getTenKhoan());
        values.put("loaiKhoan", khoan.getLoaiKhoan());
        return db.insert("KHOAN", null, values);
    }

    public long update(Khoan khoan) {
        ContentValues values = new ContentValues();
        values.put("maKhoan", khoan.getMaKhoan());
        values.put("tenKhoan", khoan.getTenKhoan());
        values.put("loaiKhoan", khoan.getLoaiKhoan());
        return db.update("KHOAN", values, "maKhoan=?", new String[]{khoan.getMaKhoan()});
    }

    public long delete(String maKhoan) {
        return db.delete("KHOAN", "maKhoan=?", new String[]{maKhoan});
    }

    public List<Khoan> getKhoan(String sql, String... a) {
        List<Khoan> list = new ArrayList<Khoan>();
        Cursor c = db.rawQuery(sql, a);
        while (c.moveToNext()) {
            Khoan khoan = new Khoan();
            khoan.setMaKhoan(c.getString(c.getColumnIndex("maKhoan")));
            khoan.setTenKhoan(c.getString(c.getColumnIndex("tenKhoan")));
            khoan.setLoaiKhoan(c.getString(c.getColumnIndex("loaiKhoan")));
            list.add(khoan);
        }
        return list;
    }

    public List<Khoan> getTenKhoan() {
        String sql = "select tenKhoan from KHOAN where Khoan.maKhoan = GIAODICH.maKhoan";
        return getKhoan(sql);
    }

    //get All
    public List<Khoan> getAll() {
        String sql = "select * from KHOAN";
        return getKhoan(sql);
    }

    //get Khoản theo mã Khoản
    public Khoan getMaKhoan(String maKhoan) {
        String sql = "select * from KHOAN where makhoan=?";
        List<Khoan> list = getKhoan(sql, maKhoan);
        return list.get(0);
    }

    //get Khoản theo loại khoản
    public List<Khoan> getLoaiKhoan(String loaiKhoan) {
        String sql = "select * from KHOAN where loaiKhoan=?";
        List<Khoan> list = getKhoan(sql, loaiKhoan);
        return list;
    }
}
