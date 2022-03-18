package com.example.money_management.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.money_management.database.KhoanDAO;
import com.example.money_management.R;
import com.example.money_management.adapter.LoaiChiAdapter;
import com.example.money_management.model.Khoan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiChiFragment extends Fragment {
    List<Khoan> list;
    RecyclerView recyclerView;
    FloatingActionButton fb;
    KhoanDAO khoanDAO;
    LoaiChiAdapter loaiChiAdapter;
    public LoaiChiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_loai_chi, container, false);
        recyclerView = view.findViewById(R.id.lvLoaiChi);
        fb = view.findViewById(R.id.btnAddLoaiChi);
        list = new ArrayList<>();
        khoanDAO = new KhoanDAO(getActivity());
        list = khoanDAO.getLoaiKhoan("0");
        loaiChiAdapter = new LoaiChiAdapter(list, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(loaiChiAdapter);




        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.add_loai_alertdialog, null);
                EditText loaiAdd = view1.findViewById(R.id.edt_loaiAdd);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            String addLoai = loaiAdd.getText().toString();
                            khoanDAO.insert(new Khoan(addLoai,"0"));
                            loaiChiAdapter.addData(khoanDAO.getLoaiKhoan("0"));
                            recyclerView.scrollToPosition(khoanDAO.getLoaiKhoan("0").size()-1);
                        }catch (NumberFormatException e) {
                            Toast.makeText(getActivity(), "loại nhâp vào Không hợp lệ", Toast.LENGTH_SHORT).show();
                        } catch (RuntimeException e) {
                            Toast.makeText(getActivity(), "Sai", Toast.LENGTH_SHORT).show();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }

        });
        return view;
    }
}
