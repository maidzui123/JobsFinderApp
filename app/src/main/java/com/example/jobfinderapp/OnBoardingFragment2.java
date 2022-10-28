package com.example.jobfinderapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class OnBoardingFragment2 extends Fragment {
    private TextView tlTitleFm2;
    private View mView;
    public OnBoardingFragment2() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_on_boarding2, container, false);
        tlTitleFm2 = mView.findViewById(R.id.tvTitleFm2);
        String title = "Find Trusted Company";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 13,0);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 13, 20,0);
        s.setSpan(new StyleSpan(Typeface.BOLD),5,20,0);
        tlTitleFm2.setText(s);
        return mView;
    }
}