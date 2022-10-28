package com.example.jobfinderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator2;
import me.relex.circleindicator.CircleIndicator3;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private RelativeLayout rlFooter;
    private CircleIndicator3 circle_indicator;
    private Button btnNext;
    private TextView tvSkip;
    private OnBoardingAdapter onBoardingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        initUI();
        onBoardingAdapter = new OnBoardingAdapter(this);
        viewPager2.setAdapter(onBoardingAdapter);
        circle_indicator.setViewPager(viewPager2);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager2.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
                }
                else {
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                }
            }
        });
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager2.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                    viewPager2.setCurrentItem(2);
                }
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 2) {
                    btnNext.setText("Get Started");
                    tvSkip.setVisibility(View.GONE);
                }
                else {
                    btnNext.setText("Next");
                    tvSkip.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void initUI() {
        viewPager2 = findViewById(R.id.viewPager2);
        rlFooter = findViewById(R.id.rlFooter);
        circle_indicator = findViewById(R.id.circle_indicator);
        btnNext = findViewById(R.id.btnNext);
        tvSkip = findViewById(R.id.tvSkip);
    }
}