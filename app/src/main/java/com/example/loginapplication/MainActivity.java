package com.example.loginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;
    private TextView eRegister;
    private CheckBox eRememberMe;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;



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
        forgotPassword = findViewById(R.id.forgotPassword);



        eAttemptsInfo.setText("No of attempts remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);

//        if (user != null) {
//            finish();
//            startActivity(new Intent(MainActivity.this, HomePageActivity.class));
//        }

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(eName.getText().toString(),ePassword.getText().toString());
            }
        });


        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResetPasswordActivity.class));
            }
        });


    }

    private void validate(String userName, String password) {

        progressDialog.setMessage("Please wait for a second");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    eAttemptsInfo.setText("No of attempts remaining:" + counter);
                    progressDialog.dismiss();
                    if(counter ==0){
                        eLogin.setEnabled(false);
                    }
                }
            }
        });
    }
}


