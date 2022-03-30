package com.example.money_management.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.money_management.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class   ExitFragment extends Fragment {

    public ExitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exit, container, false);

        // backstack
        System.exit(1);
        return view;
    }
}
