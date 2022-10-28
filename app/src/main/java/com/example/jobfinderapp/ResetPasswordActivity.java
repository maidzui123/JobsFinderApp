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

public class ResetPasswordActivity extends AppCompatActivity {
    private TextView tvResetPassTitle;
    private Button btnResetPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        tvResetPassTitle = findViewById(R.id.tvResetPassTitle);
        btnResetPass = findViewById(R.id.btnResetPass);
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });
        String title = "Reset Password";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 6, 14,0);
        tvResetPassTitle.setText(s);
    }
}