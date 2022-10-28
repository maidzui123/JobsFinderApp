package com.example.jobfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private ImageView tvSplashLogo;
    private static final String KEY_FIRST_INSTALL = "KEY_FIRST_INSTALL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvSplashLogo = findViewById(R.id.tvSplashLogo);
        startAnimation();
        MySharePreferences mySharePreferences = new MySharePreferences(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mySharePreferences.getBooleanValue(KEY_FIRST_INSTALL)) {
                    // Main
                    startActivity(SignInActivity.class);
                }
                else {
                    // On Boarding
                    startActivity(OnBoardingActivity.class);
                    mySharePreferences.putBooleanValue(KEY_FIRST_INSTALL, true);
                }
            }
        },
                3000);
    }
    private void startAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        tvSplashLogo.startAnimation(animation);
    }

    private void startActivity(Class<?> cls) {
        Intent intent= new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}