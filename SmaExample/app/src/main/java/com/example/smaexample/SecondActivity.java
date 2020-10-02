package com.example.smaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private String extraInfo;
    private TextView mainTextView;
    private EditText mainEditText;
    private Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initializeViews();
        Intent intent = getIntent();
        extraInfo = intent.getStringExtra("extra");
        mainTextView.setText(extraInfo);
    }


    private void initializeViews()
    {
        mainTextView = findViewById(R.id.tv_second_text);
        mainEditText = findViewById(R.id.et_second_input);
        mainButton = findViewById(R.id.btn_second_button);
    }
}