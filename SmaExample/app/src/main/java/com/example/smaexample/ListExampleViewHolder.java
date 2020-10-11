package com.example.smaexample;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListExampleViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTv;
    private TextView firstnameTv;

    public ListExampleViewHolder(@NonNull View itemView) {
        super(itemView);
        initializeViews();
    }

    private void initializeViews()
    {
        nameTv = itemView.findViewById(R.id.tv_row_example_name);
        firstnameTv = itemView.findViewById(R.id.tv_row_example_firstname);
    }

    public void setValues(String name, String firstname){
        nameTv.setText(name);
        firstnameTv.setText(firstname);
    }
}