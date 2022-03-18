package com.example.money_management.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.money_management.database.DbHelper;
import com.example.money_management.model.LoaiChi;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiDao {
    SQLiteDatabase sql;
    DbHelper dbHelper;
    public LoaiChiDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public void insert(LoaiChi loaiChi){
        sql = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKhoan",loaiChi.tenKhoan);
        values.put("kieuGiaoDich",loaiChi.LOAICHI);
        sql.insert("khoanThuChi",null,values);
    }
    public void delete(int position){
        sql = dbHelper.getWritableDatabase();
        sql.delete("khoanThuChi","idKhoan=?", new String[]{String.valueOf(position)});
        sql.delete("giaoDich","idKhoan=?", new String[]{String.valueOf(position)});
    }
    public void update(LoaiChi loaiChi){
        sql = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKhoan",loaiChi.tenKhoan);
        sql.update("khoanThuChi",values,"idLoai=?",new String[]{String.valueOf(loaiChi.idKhoan)});
    }
    public List<LoaiChi> getAll(){
        sql =dbHelper.getReadableDatabase();
        String[] whereArgs = {"0"};
        Cursor cs = sql.query("khoanThuChi",null,"loaiKhoan",whereArgs,null,null,null);
        List<LoaiChi> list = new ArrayList<>();
        while (!cs.isAfterLast()){
            try {
                int idKhoan =cs.getInt(0);
                String tenKhoan=cs.getString(1);
                list.add(new LoaiChi(idKhoan,tenKhoan));
                cs.moveToNext();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        cs.close();
        return list;
    }
}
