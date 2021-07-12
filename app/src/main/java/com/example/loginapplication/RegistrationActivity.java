package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText eRegName;
    private EditText eRegPassword;
    private Button eRegister;
    private EditText eRegEmail;

    public static Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.buttonRegister);
        eRegEmail = findViewById(R.id.etRegEmail);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regUsername = eRegName.getText().toString();
                String regPassword = eRegPassword.getText().toString();
                String regEmail = eRegEmail.getText().toString();

                if(validate(regUsername,regPassword,regEmail)){
                    storage = new Storage(regUsername,regPassword,regEmail);
                    Toast.makeText(RegistrationActivity.this,"Registration successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                    startActivity(intent);
                }


            }
        });
    }
    private boolean validate(String username, String password, String email){
        if(username.isEmpty() || password.length() < 7 || email.isEmpty()){
            Toast.makeText(RegistrationActivity.this,"Please enter all details, password should be at least 7 characters",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}