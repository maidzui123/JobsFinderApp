package com.example.jobfinderapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OnBoardingAdapter extends FragmentStateAdapter {
    public OnBoardingAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new OnBoardingFragment2();
            case 2:
                return new OnBoardingFragment3();
            case 0:
            default:
                return new OnBoardingFragment1();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
