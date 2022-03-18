package com.example.money_management.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.money_management.R;
import com.example.money_management.adapter.ThongKeVPAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeFragment extends Fragment {

    public ThongKeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all,container,false);
        TabLayout tableLayout = view.findViewById(R.id.tablayout);
        final ViewPager viewPager = view.findViewById(R.id.viewpager);
        ThongKeVPAdapter adapter = new ThongKeVPAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tableLayout.addTab(tableLayout.newTab().setText("Thống kê Chi"));
        tableLayout.addTab(tableLayout.newTab().setText("Thống Kê Thu"));
        tableLayout.setTabGravity(TabLayout.MODE_SCROLLABLE);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableLayout));
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}
