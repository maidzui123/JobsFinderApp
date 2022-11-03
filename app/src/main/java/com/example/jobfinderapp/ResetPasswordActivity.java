package com.example.jobfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ResetPasswordActivity extends AppCompatActivity {
    private TextView tvResetPassTitle;
    private Button btnResetPass;

    private TextInputLayout tilResetPassPassword, tilConfirmResetPassPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        tvResetPassTitle = findViewById(R.id.tvResetPassTitle);
        btnResetPass = findViewById(R.id.btnResetPass);

        tilResetPassPassword = findViewById(R.id.tilResetPassPassword);
        tilConfirmResetPassPassword = findViewById(R.id.tilConfirmResetPassPassword);
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePassword() && validateConfirmPassword()) {
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                }            }
        });
        String title = "Reset Password";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 6, 14,0);
        tvResetPassTitle.setText(s);
    }
    private Boolean validatePassword() {
        String val = tilResetPassPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$";
        if (val.isEmpty()) {
            tilResetPassPassword.setError("Password can't be empty!");
            return false;
        } else if (!val.matches(passwordVal)) {
            tilResetPassPassword.setError("Password is too weak!");
            return false;
        } else {
            tilResetPassPassword.setError(null);
            tilResetPassPassword.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateConfirmPassword() {
        String val1 = tilResetPassPassword.getEditText().getText().toString();
        String val2 = tilConfirmResetPassPassword.getEditText().getText().toString();
        if (val2.isEmpty()) {
            tilConfirmResetPassPassword.setError("Confirm Password can't be empty!");
            return false;
        } else if (!val1.matches(val2)) {
            tilConfirmResetPassPassword.setError("Please make sure your passwords match!");
            return false;
        } else {
            tilConfirmResetPassPassword.setError(null);
            tilConfirmResetPassPassword.setErrorEnabled(false);
            return true;
        }
    }
}