package com.example.money_management.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.money_management.database.GiaoDichDAO;
import com.example.money_management.database.KhoanDAO;
import com.example.money_management.R;
import com.example.money_management.adapter.KhoanChiAdapter;
import com.example.money_management.adapter.SpinnerAdapter;
import com.example.money_management.model.GiaoDich;
import com.example.money_management.model.Khoan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhoanChiFragment extends Fragment {
    List<GiaoDich> list;
    RecyclerView recyclerView;
    FloatingActionButton fb;
    GiaoDichDAO gdDAO;
    KhoanDAO khoanDAO;
    KhoanChiAdapter khoanChiAdapter;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public KhoanChiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan_chi, container, false);
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.lvKhoanChi);
        fb = view.findViewById(R.id.btnAddKhoanChi);
        list = new ArrayList<>();
        gdDAO = new GiaoDichDAO(getActivity());
        khoanDAO = new KhoanDAO(getActivity());
        list = gdDAO.getAllChi();

        khoanChiAdapter = new KhoanChiAdapter(list, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(khoanChiAdapter);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View n) {
                if (!haveType()) {
                    Toast.makeText(getActivity(), "Chưa có loại", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.add_khoan_alertdialog, null);
                Spinner loaiGD = view1.findViewById(R.id.spnadd);
                TextView ngayGD = view1.findViewById(R.id.edt_ngayadd);
                ImageView btnDate = view1.findViewById(R.id.btnDate);
                EditText motaGD = view1.findViewById(R.id.alertContent);
                EditText tienGD = view1.findViewById(R.id.edt_tienadd);

                SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getActivity(), new KhoanDAO(getActivity()).getLoaiKhoan("0"));
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
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            int id = loaiGD.getSelectedItemPosition();
                            String idLoai = new KhoanDAO(getActivity()).getLoaiKhoan("0").get(id).getMaKhoan();
                            Double tienNew = Double.parseDouble(tienGD.getText().toString());
                            Date soNgay = sdf.parse(ngayGD.getText().toString());
                            String mota = motaGD.getText().toString();

                            gdDAO.insert(new GiaoDich(soNgay, mota, tienNew, idLoai));
                            Snackbar.make(view.findViewById(R.id.conss), "Thêm thành công!", 2000).setActionTextColor(Color.RED).setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            }).show();
                            khoanChiAdapter.addData(gdDAO.getAllChi());
                            recyclerView.scrollToPosition(gdDAO.getAllChi().size() - 1);
                            recyclerView.setAdapter(khoanChiAdapter);
                        } catch (NumberFormatException e) {
                            Snackbar.make(view.findViewById(R.id.conss), "Tiền nhập không hợp lệ!", 2000).setActionTextColor(Color.RED).setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            }).show();
                        } catch (RuntimeException e) {
                            Toast.makeText(getActivity(), "Chưa có loại", Toast.LENGTH_SHORT).show();
                        } catch (Exception ex) {
                            Snackbar.make(view.findViewById(R.id.conss), "Ngày không hợp lệ!", 2000).setActionTextColor(Color.RED).setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            }).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button nButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                nButton.setBackgroundColor(Color.WHITE);
                nButton.setTextColor(Color.BLACK);
                Button pButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                pButton.setBackgroundColor(Color.WHITE);
                pButton.setTextColor(Color.BLACK);
            }

        });

        return view;
    }

    private String GetDay() {
        long time = System.currentTimeMillis();
        String day = sdf.format(time);
        return day;
    }

    private boolean haveType() {
        List<Khoan> list = khoanDAO.getLoaiKhoan("0");
        return !list.isEmpty();
    }
}
