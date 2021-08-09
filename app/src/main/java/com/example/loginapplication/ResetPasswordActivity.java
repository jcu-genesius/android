package com.example.loginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        passwordEmail = findViewById(R.id.etPasswordEmail);
        resetPassword = findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = passwordEmail.getText().toString().trim();

                if(userEmail.isEmpty()){
                    Toast.makeText(ResetPasswordActivity.this, "Please enter your registered email ID  ", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPasswordActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ResetPasswordActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(ResetPasswordActivity.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}