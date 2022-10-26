package com.example.jobfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    private TextView tvSignUpTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tvSignUpTitle = findViewById(R.id.tvSignUpTitle);
        String title = "Sign Up";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#00FF7F")), 5, 7,0);
        tvSignUpTitle.setText(s);
    }
}