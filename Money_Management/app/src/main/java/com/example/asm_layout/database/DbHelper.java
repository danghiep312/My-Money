package com.example.money_management.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"QuanLyThuChi.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table khoanThuChi(idKhoan integer primary key autoincrement, tenKhoan text not null , loaiKhoan integer)");
        db.execSQL("create table giaoDich(idGiaoDich integer primary key autoincrement, idKhoan integer, ngayGiaoDich date, noiDung text, soTien float, loaiKhoan integer, foreign key(idKhoan) references khoanThuChi(idKhoan))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS khoanThuChi");
        db.execSQL("DROP TABLE IF EXISTS giaoDich");
        onCreate(db);
    }
}
