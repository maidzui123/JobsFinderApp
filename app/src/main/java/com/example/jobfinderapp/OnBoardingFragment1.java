package com.example.jobfinderapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class OnBoardingFragment1 extends Fragment {
    private TextView tlTitleFm1;
    private View mView;
    public OnBoardingFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_on_boarding1, container, false);
        tlTitleFm1 = mView.findViewById(R.id.tvTitleFm1);
        String title = "Easy Online Apply";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 0, 5,0);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 5, 17,0);
        s.setSpan(new StyleSpan(Typeface.BOLD),0,12,0);
        tlTitleFm1.setText(s);
        return mView;

    }
}