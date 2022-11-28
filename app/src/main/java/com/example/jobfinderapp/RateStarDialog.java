package com.example.jobfinderapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RateStarDialog extends Fragment {
    public RateStarDialog() {
    }
    private RatingBar rbRatingBar;
    private Button btnRateStar;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_dialog_ratestar, container, false);
        rbRatingBar = mView.findViewById(R.id.rbRatingBar);
        btnRateStar = mView.findViewById(R.id.btnRateStar);
        rbRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String message = null;
                switch((int) rating) {
                    case 1:
                        message = "Sorry to hear that! :(";
                    case 2:
                        message = "You always accept suggestions!";
                    case 3:
                        message = "Good enough!";
                    case 4:
                        message = "Great! Thank you!";
                    case 5:
                        message = "Awesome! You are the best!";
                }
            }
        });
        return mView;
    }
}
