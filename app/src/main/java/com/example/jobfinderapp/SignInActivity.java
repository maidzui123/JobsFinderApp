package com.example.jobfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private TextView tvSignInTitle;
    private Button btnSignIn;
    private TextView tvForgetPass;
    private  TextView tvSignIn;

    private FirebaseAuth firebaseAuth;
    private TextInputLayout tilSignInUsername, tilSignInPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tvSignInTitle = findViewById(R.id.tvSignInTitle);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignIn = findViewById(R.id.tvSignIn);
        tvForgetPass = findViewById(R.id.tvForgetPass);
        tilSignInUsername = findViewById(R.id.tilSignInUsername);
        tilSignInPassword = findViewById(R.id.tilSignInPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
        tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
            }
        });
        String title = "Sign In";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 5, 7,0);
        tvSignInTitle.setText(s);
    }
    private Boolean validateUsername() {
        String val = tilSignInUsername.getEditText().getText().toString();
        if (val.isEmpty()) {
            tilSignInUsername.setError("Username can't be empty!");
            return false;
        } else {
            tilSignInUsername.setError(null);
            tilSignInUsername.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword() {
        String val = tilSignInPassword.getEditText().getText().toString();
        if (val.isEmpty()) {
            tilSignInPassword.setError("Password can't be empty!");
            return false;
        } else {
            tilSignInPassword.setError(null);
            tilSignInPassword.setErrorEnabled(false);
            return true;
        }
    }
    private void loginUser(){
        String userName= tilSignInUsername.getEditText().getText().toString().trim();
        String password = tilSignInPassword.getEditText().getText().toString().trim();
        if(!validatePassword() | !validateUsername())
        {
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(userName,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(SignInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}