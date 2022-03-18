package com.example.money_management.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.money_management.model.Khoan;
import com.example.money_management.R;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    List<? extends Khoan> list;

    public SpinnerAdapter(Context context, List<? extends Khoan> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.spinner_layout,parent,false);
        TextView tv_id=v.findViewById(R.id.sp_id);
        TextView tv_Loai=v.findViewById(R.id.sp_name);
        tv_id.setText(String.valueOf(position+1));
        tv_Loai.setText(list.get(position).getTenKhoan());
        return v;
    }
}
