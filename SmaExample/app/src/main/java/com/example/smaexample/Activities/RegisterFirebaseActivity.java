package com.example.smaexample.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smaexample.Models.UserModel;
import com.example.smaexample.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.smaexample.Helpers.FirebaseHelper.usersDatabase;

public class RegisterFirebaseActivity extends AppCompatActivity {
    private TextInputEditText emailEt;
    private TextInputEditText passwordEt;
    private TextInputEditText nameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firebase);
        initializeViews();
    }

    private void initializeViews() {
        emailEt = findViewById(R.id.et_register_email);
        passwordEt = findViewById(R.id.et_register_password);
        nameEt = findViewById(R.id.et_register_name);
    }

    public void onRegister(View view) {
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (emailEt.getText().toString().isEmpty() || passwordEt.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        final String email = emailEt.getText().toString();
        final String password = passwordEt.getText().toString();
        final String name = nameEt.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null){
                        return;
                    }
                    UserModel userModel = new UserModel(name, "Cosmin", email, 22);
                    usersDatabase.child(user.getUid()).setValue(userModel);
                    startActivity(new Intent(RegisterFirebaseActivity.this, FirebaseLoginActivity.class));
                }
                else {
                    Toast.makeText(RegisterFirebaseActivity.this, "Register failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}