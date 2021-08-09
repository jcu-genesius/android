package com.example.loginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eRegName;
    private EditText eRegPassword;
    private Button eRegister;
    private EditText eRegEmail;
    private FirebaseAuth firebaseAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.buttonRegister);
        eRegEmail = findViewById(R.id.etRegEmail);

        firebaseAuth = FirebaseAuth.getInstance();


        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regUsername = eRegName.getText().toString().trim();
                String regPassword = eRegPassword.getText().toString().trim();
                String regEmail = eRegEmail.getText().toString().trim();

                if (validate(regUsername, regPassword, regEmail)) {

                    firebaseAuth.createUserWithEmailAndPassword(regEmail, regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });



                }


            }
        });
    }

    private boolean validate(String username, String password, String email) {
        if (username.isEmpty() || password.length() < 7 || email.isEmpty()) {
            Toast.makeText(RegistrationActivity.this, "Please enter all details, password should be at least 7 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}