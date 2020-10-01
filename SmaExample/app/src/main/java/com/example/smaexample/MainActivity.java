package com.example.smaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mainTextView;
    private EditText mainEditText;
    private Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setOnClickListeners();
    }

    private void changeTextViewText()
    {
        String inputValue = mainEditText.getText().toString();
        if (inputValue.isEmpty())
        {
            return;
        }
        else
        {
            mainTextView.setText((inputValue));
        }
    }

    private void setOnClickListeners()
    {
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTextViewText();
            }
        });
    }

    private void initializeViews()
    {
        mainTextView = findViewById(R.id.tv_main_text);
        mainEditText = findViewById(R.id.et_main_input);
        mainButton = findViewById(R.id.btn_main_button);
    }
}