package com.example.smaexample.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smaexample.Helpers.FirebaseHelper;
import com.example.smaexample.Helpers.StorageHelper;
import com.example.smaexample.Models.UserModel;
import com.example.smaexample.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class FirebaseLoginActivity extends AppCompatActivity {

    private TextInputEditText emailEt;
    private TextInputEditText passwordEt;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_login);
        initializeViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            startActivity(new Intent(FirebaseLoginActivity.this, FirstMenuActivity.class));
        }
    }

    private void initializeViews() {
        emailEt = findViewById(R.id.et_login_email);
        passwordEt = findViewById(R.id.et_login_password);
    }

    public void goToRegister(View view) {
        startActivity(new Intent(FirebaseLoginActivity.this, RegisterFirebaseActivity.class));
    }

    public void onLogin(View view) {
        firebaseAuth = FirebaseAuth.getInstance();
        if (emailEt.getText().toString().isEmpty() || passwordEt.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            FirebaseHelper.usersDatabase.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    UserModel userModel = snapshot.getValue(UserModel.class);
                                    StorageHelper.getInstance().setUserModel(userModel);
                                    startActivity(new Intent(FirebaseLoginActivity.this, FirstMenuActivity.class));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        else {
                            Toast.makeText(FirebaseLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}