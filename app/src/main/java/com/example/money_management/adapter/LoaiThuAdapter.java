package com.example.money_management.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_management.database.KhoanDAO;
import com.example.money_management.R;
import com.example.money_management.model.Khoan;

import java.util.List;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.ViewHolder> {

    List<Khoan> list;
    Context context;
    KhoanDAO khoanDAO;
    public LoaiThuAdapter(List<Khoan> list, Context context){
        this.list = list;
        this.context = context;
        khoanDAO = new KhoanDAO(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.loai_one_item,parent,false);
        khoanDAO= new KhoanDAO(context);
        return new LoaiThuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtLoai.setText(list.get(position).getTenKhoan());
        holder.btnDeleteLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProcess(position);
            }
        });
        holder.btnEditLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Khoan khoan=list.get(position);
                showDialog(khoan);
            }
        });
    }

    public void deleteProcess(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.delete_alertdialog, null);
        TextView content = view.findViewById(R.id.alertContent);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Khoan khoan = list.get(position);
                khoanDAO.delete(khoan.getMaKhoan());
                list.remove(khoan);
                notifyItemRemoved(position);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button nButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nButton.setBackgroundColor(Color.WHITE);
        nButton.setTextColor(Color.BLACK);
        Button pButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pButton.setBackgroundColor(Color.WHITE);
        pButton.setTextColor(Color.BLACK);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void showDialog(Khoan khoan){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.edit_loai_alert_dialog,null);
        EditText tenKhoan = view.findViewById(R.id.edt_loai);


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String maKhoan1 = khoan.getMaKhoan();
                String tenKhoan1 = tenKhoan.getText().toString();
                khoanDAO.update(new Khoan(maKhoan1,tenKhoan1,"1"));
                list = new KhoanDAO(context).getLoaiKhoan("1");
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button nButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nButton.setBackgroundColor(Color.WHITE);
        nButton.setTextColor(Color.BLACK);
        Button pButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pButton.setBackgroundColor(Color.WHITE);
        pButton.setTextColor(Color.BLACK);
    }
    public void addData(List<Khoan> list) {
        if (list != null) {
            this.list = list;
            notifyItemInserted(list.size() - 1);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtstt, txtLoai;
        public TextView btnEditLoai, btnDeleteLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtstt = itemView.findViewById(R.id.txtstt);
            txtLoai = itemView.findViewById(R.id.txtLoai);
            btnEditLoai = itemView.findViewById(R.id.btnEditLoai);
            btnDeleteLoai = itemView.findViewById(R.id.btnDeleteLoai);
        }
    }
}
