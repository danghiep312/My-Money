package com.example.money_management.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.money_management.database.DbHelper;
import com.example.money_management.model.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuDao {
    SQLiteDatabase sql;
    DbHelper dbHelper;
    public KhoanThuDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public void insert(KhoanThu khoanThu){
        sql = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idKhoan",khoanThu.idKhoan);
        values.put("ngayGiaoDich",khoanThu.ngayGiaoDich);
        values.put("noiDung",khoanThu.noiDung);
        values.put("soTien",khoanThu.soTien);
        values.put("kieuGiaoDich",khoanThu.LOAIKHOAN);
        sql.insert("giaoDich",null,values);
    }
    public void delete(int position){
        sql = dbHelper.getWritableDatabase();
        sql.delete("giaoDich", "idGiaoDich=?",new String[]{String.valueOf(position)});
    }
    public void update(KhoanThu khoanThu){
        sql = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idKhoan",khoanThu.idKhoan);
        values.put("ngayGiaoDich",khoanThu.ngayGiaoDich);
        values.put("noiDung",khoanThu.noiDung);
        values.put("soTien",khoanThu.soTien);
        values.put("kieuGiaoDich",khoanThu.LOAIKHOAN);
        sql.update("giaoDich",values,"idGiaoDich=?",new String[]{String.valueOf(khoanThu.idGiaoDich)});
    }
    public List<KhoanThu> getAll(){
        sql = dbHelper.getWritableDatabase();
        String[] whereArgs={"1"};
        Cursor cs = sql.query("giaoDich",null,"kieuGiaoDich",whereArgs,null,null,null);
        List<KhoanThu> list = new ArrayList<>();
        while (!cs.isAfterLast()){
            try {
                int idGiaoDich =cs.getInt(0);
                int idKhoan=cs.getInt(1);
                String ngayGiaoDich =String.valueOf(cs.getString(2));
                String noiDung =String.valueOf(cs.getString(3));
                int soTien=cs.getInt(4);
                list.add(new KhoanThu(idGiaoDich,idKhoan,ngayGiaoDich,noiDung,soTien));
                cs.moveToNext();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        cs.close();
        return list;
    }
}
