package com.example.jobfinderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomFragment extends Fragment {
    public BottomFragment() {
        // Required empty public constructor
    }
    String[] job_items = {"  Programming", "  Marketing", "  Design", "  Healthcare"};
    String[] job_time = {"  Full-time", "  Part-time"};
    String[] job_location = {"  Ho Chi Minh", "  Ha Noi", " Ben Tre", "  Quang Tri"};
    AutoCompleteTextView autoCompleteTxt, autoCompleteTxt1, autoCompleteTxt2;
    ArrayAdapter<String> adapterItems, adapterItems1, adapterItems2;
    private View mView;
    private Button btnSaveFilter,btnCloseFilter;
    private TextView tvResetFilter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
        autoCompleteTxt = mView.findViewById(R.id.autoCompleteTxt);
        autoCompleteTxt1 = mView.findViewById(R.id.autoCompleteTxt1);
        autoCompleteTxt2 = mView.findViewById(R.id.autoCompleteTxt2);
        btnSaveFilter = mView.findViewById(R.id.btnSaveFilter);
        btnCloseFilter = mView.findViewById(R.id.btnCloseFilter);
        tvResetFilter = mView.findViewById(R.id.tvResetFilter);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.job_dropdown_item, job_items);
        adapterItems1 = new ArrayAdapter<String>(getActivity(), R.layout.job_dropdown_item, job_time);
        adapterItems2 = new ArrayAdapter<String>(getActivity(), R.layout.job_dropdown_item, job_location);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt1.setAdapter(adapterItems1);
        autoCompleteTxt2.setAdapter(adapterItems2);
        tvResetFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTxt.setText(job_items[0],false);
                autoCompleteTxt1.setText(job_time[0],false);
                autoCompleteTxt2.setText(job_location[0],false);
            }
        });
        btnSaveFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeFragment());
            }
        });
        btnCloseFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeFragment());
            }
        });
        return mView;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flBottomFragment, fragment);
        fragmentTransaction.commit();
    }

}