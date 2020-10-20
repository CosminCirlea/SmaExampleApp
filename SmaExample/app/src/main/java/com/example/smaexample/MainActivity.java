package com.example.smaexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.smaexample.AppConstants.NAVIGATION_KEY_1;

public class MainActivity extends AppCompatActivity {
    private TextView mainTextView;
    private EditText mainEditText;
    private Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setTexts();
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
                //AICI SE EXECUTA CODUL DUPA CLICK
                changeTextViewText();
            }
        });
    }

    private void setTexts()
    {
        mainButton.setText("Schimba textul!");
        mainEditText.setHint("Introduceti alt text");
        mainEditText.setText("SMA");
    }

    private void initializeViews()
    {
        mainTextView = findViewById(R.id.tv_main_text);
        mainEditText = findViewById(R.id.et_main_input);
        mainButton = findViewById(R.id.btn_main_button);
    }

    public void activitateaUrmatoare(View view) {
        String inputValue = mainEditText.getText().toString();
        if (inputValue.isEmpty())
        {
            Toast.makeText(this, "Introduceti un text pentru a continua", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent intent = new Intent(this, RoomActivity.class);
            intent.putExtra(AppConstants.NAVIGATION_KEY_1, inputValue);
            startActivity(intent);
        }
    }
}