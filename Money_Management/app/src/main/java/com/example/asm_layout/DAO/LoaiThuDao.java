package com.example.money_management.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.money_management.database.DbHelper;
import com.example.money_management.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuDao {
    SQLiteDatabase sql;
    DbHelper dbHelper;
    public LoaiThuDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public void insert(LoaiThu loaiThu){
        sql = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKhoan",loaiThu.tenKhoan);
        values.put("kieuGiaoDich",loaiThu.LOAITHU);
        sql.insert("khoanThuChi",null,values);
    }public void delete(int position){
        sql = dbHelper.getWritableDatabase();
        sql.delete("khoanThuChi","idKhoan=?", new String[]{String.valueOf(position)});
        sql.delete("giaoDich","idKhoan=?", new String[]{String.valueOf(position)});
    }
    public void update(LoaiThu loaiThu){
        sql = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenKhoan",loaiThu.tenKhoan);
        sql.update("khoanThuChi",values,"idLoai=?",new String[]{String.valueOf(loaiThu.idKhoan)});
    }
    public List<LoaiThu> getAll(){
        sql =dbHelper.getReadableDatabase();
        String[] whereArgs = {"0"};
        Cursor cs = sql.query("khoanThuChi",null,"loaiKhoan",whereArgs,null,null,null);
        List<LoaiThu> list = new ArrayList<>();
        while (!cs.isAfterLast()){
            try {
                int idKhoan =cs.getInt(0);
                String tenKhoan=cs.getString(1);
                list.add(new LoaiThu(idKhoan,tenKhoan));
                cs.moveToNext();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        cs.close();
        return list;
    }

}
