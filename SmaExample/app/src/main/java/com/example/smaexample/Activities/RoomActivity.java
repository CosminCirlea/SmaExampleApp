package com.example.smaexample.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smaexample.AppExecutors;
import com.example.smaexample.R;
import com.example.smaexample.TestDatabase;
import com.example.smaexample.TestEntity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private TestDatabase testDatabase;
    private List<TestEntity> testEntityList;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        initializers();
    }

    private void initializers()
    {
        testDatabase = TestDatabase.getInstance(this);
        testEntityList = new ArrayList<>();
    }

    public void addToDatabase(View view) {
        final TextInputEditText inputEditText;
        TextInputLayout textInputLayout;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        new AlertDialog.Builder(this, R.style.InputDialogTheme);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.view_input_dialog, (ViewGroup) findViewById(R.id.et_input_dialog) , false);
        inputEditText = viewInflated.findViewById(R.id.et_input_dialog);
        textInputLayout = viewInflated.findViewById(R.id.til_input_dialog);
        alert.setView(viewInflated);
        alert.setTitle("Add some value in the database");
        textInputLayout.setHint("Value");
        alert.setPositiveButton("Add value", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (inputEditText.getText() == null || inputEditText.getText().toString().isEmpty())
                {
                    return;
                }
                name = inputEditText.getText().toString();
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        insertToDatabase(name);
                    }
                });
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }

    private void insertToDatabase(final String name)
    {
        class InsertValue extends AsyncTask<Void, Void, TestEntity> {

            @Override
            protected TestEntity doInBackground(Void... voids) {
                TestEntity testEntity = new TestEntity(name, "Ana");
                testDatabase.testDAO().insertAll(testEntity);
                return testEntity;
            }

            @Override
            protected void onPostExecute(TestEntity testEntity) {
                super.onPostExecute(testEntity);
            }
        }

        InsertValue insertTask = new InsertValue();
        insertTask.execute();
    }

    public void getFromDatabase(View view) {
        class GetValue extends AsyncTask<Void, Void, TestEntity> {

            @Override
            protected TestEntity doInBackground(Void... voids) {
                TestEntity testEntity = new TestEntity(name, "Ana");
                testEntityList = testDatabase.testDAO().getAll();
                return testEntity;
            }

            @Override
            protected void onPostExecute(TestEntity testEntity) {
                super.onPostExecute(testEntity);
            }
        }

        GetValue insertTask = new GetValue();
        insertTask.execute();
    }
}