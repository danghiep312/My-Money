package com.example.money_management.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"ChiTieu",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table KHOAN(maKhoan integer primary key autoincrement, tenKhoan text not null , loaiKhoan integer not null)");
        db.execSQL("create table GIAODICH(maGD integer primary key autoincrement, ngayGD date not null, motaGD text not null, tienGD double not null, maKhoan integer not null references KHOAN(maKhoan))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS KHOAN");
        db.execSQL("DROP TABLE IF EXISTS GIAODICH");
        onCreate(db);
    }
}
