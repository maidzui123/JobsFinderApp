package com.example.jobfinderapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class OnBoardingFragment3 extends Fragment {
    private TextView tlTitleFm3;
    private View mView;
    public OnBoardingFragment3() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_on_boarding3, container, false);
        tlTitleFm3 = mView.findViewById(R.id.tvTitleFm3);
        String title = "Best Way to Find Your Dream Job";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 9,0);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 22, 31,0);
        s.setSpan(new StyleSpan(Typeface.BOLD),9,31,0);
        tlTitleFm3.setText(s);
        return mView;
    }
}