package com.example.jobfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    private TextView tvSignUpTitle;
    private Button btnSignUp;
    private TextView tvSignUp;

    private TextInputLayout tilSignUpUsername, tilSignupPassword, tilSignUpEmail, tilSignUpConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tvSignUpTitle = findViewById(R.id.tvSignUpTitle);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignUp = findViewById(R.id.tvSignUp);

        tilSignUpUsername = findViewById(R.id.tilSignUpUsername);
        tilSignUpEmail = findViewById(R.id.tilSignUpEmail);
        tilSignupPassword = findViewById(R.id.tilSignUpPassword);
        tilSignUpConfirmPassword = findViewById(R.id.tilSignUpConfirmPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUsername() && validatePassword() && validateEmail() && validateConfirmPassword()) {
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                }
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });
        String title = "Sign Up";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 5, 7,0);
        tvSignUpTitle.setText(s);
    }
    private Boolean validateUsername() {
        String val = tilSignUpUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            tilSignUpUsername.setError("Username can't be empty!");
            return false;
        } else if (val.length() >= 15) {
            tilSignUpUsername.setError("Username too long!");
            return false;
        } else if (val.length() < 8) {
            tilSignUpUsername.setError("Username must have at least 8 characters!");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            tilSignUpUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            tilSignUpUsername.setError(null);
            tilSignUpUsername.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail() {
        String val = tilSignUpEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            tilSignUpEmail.setError("Email cannot be empty!");
            return false;
        } else if (!val.matches(emailPattern)) {
            tilSignUpEmail.setError("Invalid email address!");
            return false;
        } else {
            tilSignUpEmail.setError(null);
            tilSignUpEmail.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = tilSignupPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            tilSignupPassword.setError("Password can't be empty!");
            return false;
        } else if (!val.matches(passwordVal)) {
            tilSignupPassword.setError("Password is too weak!");
            return false;
        } else {
            tilSignupPassword.setError(null);
            tilSignupPassword.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateConfirmPassword() {
        String val1 = tilSignupPassword.getEditText().getText().toString();
        String val2 = tilSignUpConfirmPassword.getEditText().getText().toString();
        if (val2.isEmpty()) {
            tilSignUpConfirmPassword.setError("Confirm Password can't be empty!");
            return false;
        } else if (!val1.matches(val2)) {
            tilSignUpConfirmPassword.setError("Please make sure your passwords match!");
            return false;
        } else {
            tilSignUpConfirmPassword.setError(null);
            tilSignUpConfirmPassword.setErrorEnabled(false);
            return true;
        }
    }

}