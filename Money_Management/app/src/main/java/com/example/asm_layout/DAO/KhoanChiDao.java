package com.example.money_management.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.money_management.database.DbHelper;
import com.example.money_management.model.KhoanChi;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiDao {
    SQLiteDatabase sql;
    DbHelper dbHelper;
    public KhoanChiDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public void insert(KhoanChi khoanChi){
        sql= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idKhoan",khoanChi.idKhoan);
        values.put("ngayGiaoDich",khoanChi.ngayGiaoDich);
        values.put("noiDung",khoanChi.noiDung);
        values.put("soTien",khoanChi.soTien);
        values.put("loaiKhoan",khoanChi.LOAIKHOAN);
        sql.insert("giaoDich",null,values);
    }
    public void delete(int position){
        sql = dbHelper.getWritableDatabase();
        sql.delete("giaoDich","idGiaoDich=?",new String[]{String.valueOf(position)});
    }
    public void update(KhoanChi khoanChi){
        sql = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("idKhoan",khoanChi.idKhoan);
        values.put("ngayGiaoDich",khoanChi.ngayGiaoDich);
        values.put("noiDung",khoanChi.noiDung);
        values.put("soTien",khoanChi.soTien);
        values.put("loaiKhoan",khoanChi.LOAIKHOAN);
        sql.update("giaoDich",values,"idGiaoDich", new String[]{String.valueOf(khoanChi.idGiaoDich)});
    }
    public List<KhoanChi> getAll(){
        sql = dbHelper.getReadableDatabase();
        String[] whereArgs={"0"};
        Cursor cs = sql.query("giaoDich",null,"kieuGiaoDich",whereArgs,null,null,null);
        List<KhoanChi> list = new ArrayList<>();
        while (!cs.isAfterLast()){
            try {
                int idGiaoDich =cs.getInt(0);
                int idKhoan=cs.getInt(1);
                String ngayGiaoDich =String.valueOf(cs.getString(2));
                String noiDung =String.valueOf(cs.getString(3));
                int soTien=cs.getInt(4);
                list.add(new KhoanChi(idGiaoDich,idKhoan,ngayGiaoDich,noiDung,soTien));
                cs.moveToNext();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        cs.close();
        return list;
    }
}

