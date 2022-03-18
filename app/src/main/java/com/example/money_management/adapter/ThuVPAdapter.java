package com.example.money_management.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.money_management.fragment.KhoanThuFragment;
import com.example.money_management.fragment.LoaiThuFragment;

public class ThuVPAdapter extends FragmentStatePagerAdapter {
    public ThuVPAdapter(FragmentManager fm){
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                KhoanThuFragment f0 = new KhoanThuFragment();
                return f0;
            case 1:
                LoaiThuFragment f1 = new LoaiThuFragment();
                return f1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
