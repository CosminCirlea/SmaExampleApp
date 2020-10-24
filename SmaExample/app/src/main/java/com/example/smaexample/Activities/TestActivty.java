package com.example.smaexample.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smaexample.ListExampleAdapter;
import com.example.smaexample.ListExampleModel;
import com.example.smaexample.R;

import java.util.ArrayList;
import java.util.List;

public class TestActivty extends AppCompatActivity {

    private RecyclerView mondayRv;
    private ListExampleAdapter mondayAdapter;
    private List<ListExampleModel> mondayModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activty);
        initializeViews();
        initializeList();
        setRecyclerView();
    }

    private void initializeList()
    {
        mondayModelList = new ArrayList<>();
        mondayModelList.add(new ListExampleModel("Ana", "Vasile"));
        mondayModelList.add(new ListExampleModel("Dan", "Maria"));
        mondayModelList.add(new ListExampleModel("Ana", "Vasile"));
        mondayModelList.add(new ListExampleModel("Dan", "Maria"));
        mondayModelList.add(new ListExampleModel("Ana", "Vasile"));
        mondayModelList.add(new ListExampleModel("Dan", "Maria"));
        mondayModelList.add(new ListExampleModel("Ana", "Vasile"));
        mondayModelList.add(new ListExampleModel("Dan", "Maria"));
        mondayModelList.add(new ListExampleModel("Ana", "Vasile"));
        mondayModelList.add(new ListExampleModel("Dan", "Maria"));
        mondayModelList.add(new ListExampleModel("Dan", "Maria"));
        mondayModelList.add(new ListExampleModel("Ana", "Vasile"));
        mondayModelList.add(new ListExampleModel("Dan", "Maria"));
    }

    private void setRecyclerView()
    {
        mondayAdapter = new ListExampleAdapter(mondayModelList);
        mondayRv.setLayoutManager(new LinearLayoutManager(this));
        mondayRv.setAdapter(mondayAdapter);
    }

    private void initializeViews()
    {
        mondayRv = findViewById(R.id.rv_test_list);
    }
}