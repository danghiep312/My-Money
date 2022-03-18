package com.example.money_management.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_management.database.GiaoDichDAO;
import com.example.money_management.database.KhoanDAO;
import com.example.money_management.R;
import com.example.money_management.model.GiaoDich;
import com.example.money_management.model.Khoan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.ViewHolder> {
    List<GiaoDich> list;
    Context context;
    GiaoDichDAO gdDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public KhoanChiAdapter(List<GiaoDich> list, Context context) {
        this.list = list;
        this.context = context;
        gdDAO = new GiaoDichDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khoan_one_item, parent, false);
        gdDAO = new GiaoDichDAO(context);
        return new KhoanChiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ngayGD.setText(sdf.format(list.get(position).getNgayGD()));
        holder.motaGD.setText(list.get(position).getMotaGD());
        holder.tienGD.setText(String.valueOf(list.get(position).getTienGD()));
        List<Khoan> khoanList = new KhoanDAO(context).getLoaiKhoan("0");
        try {
            String i = gdDAO.getTenKhoan(list.get(position).getMaKhoan());
            holder.loaiGD.setText(i);
//            holder.loaiGD.setText(list.get(position).maKhoan);
        } catch (Exception e) {
            holder.loaiGD.setText("Chưa có loại!");
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GiaoDich giaoDich = list.get(position);
                gdDAO.delete(giaoDich.getMaGD());
                list.remove(giaoDich);
                notifyItemRemoved(position);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiaoDich giaoDich = list.get(position);
                showDialog(giaoDich);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void showDialog(GiaoDich giaoDich) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.edit_alert_dialog, null);
        Spinner loaiGD = view.findViewById(R.id.spn);
        TextView ngayGD = view.findViewById(R.id.edt_ngay);
        EditText motaGD = view.findViewById(R.id.edt_noiDung);
        EditText tienGD = view.findViewById(R.id.edt_tien);
        ImageView btnDate = view.findViewById(R.id.btnDateE);
        tienGD.setText(String.valueOf(giaoDich.getTienGD()));
        ngayGD.setText(sdf.format(giaoDich.getNgayGD()));
        motaGD.setText(giaoDich.getMotaGD());
        List<Khoan> khoanList = new KhoanDAO(context).getLoaiKhoan("0");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(context, khoanList);
        loaiGD.setAdapter(spinnerAdapter);
        String date = GetDay();
        ngayGD.setText(date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int myear = c.get(Calendar.YEAR);
                int mmonth = c.get(Calendar.MONTH);
                int mday = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        if (month < 9) {
                            ngayGD.setText(year + "-" + "0" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            ngayGD.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                }, myear, mmonth, mday);
                datePickerDialog.show();
            }
        });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {

                    String maGD1 = giaoDich.getMaGD();
                    int id = loaiGD.getSelectedItemPosition();
                    String idLoai = new KhoanDAO(context).getLoaiKhoan("0").get(id).getMaKhoan();
                    Double tienNew = Double.parseDouble(tienGD.getText().toString());
                    Date soNgay = sdf.parse(ngayGD.getText().toString());
                    String mota = motaGD.getText().toString();
                    gdDAO.update(new GiaoDich(maGD1, soNgay, mota, tienNew, idLoai));
                    list = new GiaoDichDAO(context).getAllChi();
                    notifyDataSetChanged();
                } catch (Exception e) {

                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setView(view);
        builder.show();
    }

    public void addData(List<GiaoDich> list) {
        if (list != null) {
            this.list = list;
            notifyItemInserted(list.size() - 1);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ngayGD, motaGD, tienGD, loaiGD;
        public TextView edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ngayGD = itemView.findViewById(R.id.txtNgay);
            motaGD = itemView.findViewById(R.id.txtnoiDung);
            tienGD = itemView.findViewById(R.id.txtTien);
            loaiGD = itemView.findViewById(R.id.txtLoai);
            delete = itemView.findViewById(R.id.btnDelete);
            edit = itemView.findViewById(R.id.btnEdit);
        }
    }

    private String GetDay() {
        long time = System.currentTimeMillis();
        String day = sdf.format(time);
        return day;
    }
}
