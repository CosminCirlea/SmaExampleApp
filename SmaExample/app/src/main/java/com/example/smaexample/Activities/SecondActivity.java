package com.example.smaexample.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.smaexample.AppConstants;
import com.example.smaexample.adapters.ListExampleAdapter;
import com.example.smaexample.Models.ListExampleModel;
import com.example.smaexample.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private String extraInfo;
    private RecyclerView exampleListRv;
    private ListExampleAdapter listExampleAdapter;
    private List<ListExampleModel> exampleModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initializeViews();
        Intent intent = getIntent();
        extraInfo = intent.getStringExtra(AppConstants.NAVIGATION_KEY_1);
        initializeList();
        setRecyclerView();
    }

    private void initializeList()
    {
        exampleModelList = new ArrayList<>();
        exampleModelList.add(new ListExampleModel("Popescu", "Ana", 23));
        exampleModelList.add(new ListExampleModel("Escu", "Pavel", 19));
        exampleModelList.add(new ListExampleModel("Dinescu", "Paul", 33));
        exampleModelList.add(new ListExampleModel("Popescu", "Ana", 23));
        exampleModelList.add(new ListExampleModel("Escu", "Pavel", 19));
        exampleModelList.add(new ListExampleModel("Dinescu", "Paul", 33));
        exampleModelList.add(new ListExampleModel("Popescu", "Ana", 23));
        exampleModelList.add(new ListExampleModel("Escu", "Pavel", 19));
        exampleModelList.add(new ListExampleModel("Dinescu", "Paul", 33));
    }

    private void setRecyclerView()
    {
        listExampleAdapter = new ListExampleAdapter(exampleModelList);
        exampleListRv.setLayoutManager(new LinearLayoutManager( this));
        exampleListRv.setAdapter(listExampleAdapter);
    }

    private void initializeViews()
    {
        exampleListRv = findViewById(R.id.rv_secondary_list);
    }
}