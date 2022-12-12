package com.example.jobfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private TextView tvResetPassTitle;
    private Button btnResetPass;

    private TextInputLayout tilResetPasswordEmail;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        tvResetPassTitle = findViewById(R.id.tvResetPassTitle);
        btnResetPass = findViewById(R.id.btnResetPass);
        firebaseAuth = FirebaseAuth.getInstance();
        tilResetPasswordEmail = findViewById(R.id.tilResetPasswordEmail);
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
        String title = "Reset Password";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 6, 14,0);
        tvResetPassTitle.setText(s);
    }
    private Boolean validateEmail() {
        String val = tilResetPasswordEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            tilResetPasswordEmail.setError("Email cannot be empty!");
            return false;
        } else if (!val.matches(emailPattern)) {
            tilResetPasswordEmail.setError("Invalid email address!");
            return false;
        } else {
            tilResetPasswordEmail.setError(null);
            tilResetPasswordEmail.setErrorEnabled(false);
            return true;
        }
    }
    private void resetPassword() {
        String email = tilResetPasswordEmail.getEditText().getText().toString().trim();
        if (!validateEmail()) {
            return;
        }
        // Reset password khi nhap vao email
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                    Toast.makeText(getApplicationContext(), "Please check your email address!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter correct email!", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}