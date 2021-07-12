package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText eName;
    private EditText ePassword;
    private Button  eLogin;
    private TextView eAttemptsInfo;
    private TextView eRegister;



    boolean isValid = false;
    private int counter = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.buttonLogin);
        eAttemptsInfo = findViewById(R.id.tvAttempsinfo);
        eRegister = findViewById(R.id.tvRegister);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eName.getText().toString();
                String inputPasssword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPasssword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter all the information correctly",Toast.LENGTH_SHORT).show();
                }
                else {
                    isValid = validate(inputName,inputPasssword);

                    if(!isValid){
                        counter--;
                        Toast.makeText(MainActivity.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();

                        eAttemptsInfo.setText("Number of attempts remaining: " + counter);

                        if (counter == 0){
                            eLogin.setEnabled(false);
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this,HomePageActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });
    }

    private boolean validate(String name, String password){
        if(RegistrationActivity.storage != null){
            if(name.equals(RegistrationActivity.storage.getUsername()) && password.equals(RegistrationActivity.storage.getPassword())){
                return true;
            }
        }

        return false;
    }

}