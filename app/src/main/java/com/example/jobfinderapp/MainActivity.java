package com.example.jobfinderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.jobfinderapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_near:
                    replaceFragment(new NearbyFragment());
                    break;
                case R.id.nav_applied:
                    replaceFragment(new AppliedFragment());
                    break;
                case R.id.nav_account:
                    replaceFragment(new AccountFragment());
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}

//    Fragment fragment = null;
//                switch (item.getItemId()) {
//                        case R.id.nav_home:
//                        fragment = new HomeFragment();
//                        break;
//
//                        case R.id.nav_near:
//                        fragment = new NearbyFragment();
//                        break;
//
//                        case R.id.nav_applied:
//                        fragment = new AppliedFragment();
//                        break;
//                        case R.id.nav_account:
//                        fragment = new AccountFragment();
//                        break;
//                        }
//                        getSupportFragmentManager().beginTransaction().replace(R.id.body_container,fragment).commit();