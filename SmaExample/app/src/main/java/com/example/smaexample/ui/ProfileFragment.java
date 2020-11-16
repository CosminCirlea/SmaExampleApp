package com.example.smaexample.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smaexample.Helpers.StorageHelper;
import com.example.smaexample.Models.UserModel;
import com.example.smaexample.R;

public class ProfileFragment extends Fragment {

    private TextView nameTv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameTv= view.findViewById(R.id.tv_profile_name);

        UserModel userModel = StorageHelper.getInstance().getUserModel();

        return view;
    }
}