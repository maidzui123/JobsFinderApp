package com.example.jobfinderapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class BottomSheet extends Fragment {
    String[] job_items = {"Programming", "Marketing", "Design", "Healthcare"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    private View mView;
    public BottomSheet() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
        autoCompleteTxt = mView.findViewById(R.id.autoCompleteTxt);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.job_dropdown_item, job_items);
        autoCompleteTxt.setAdapter(adapterItems);

        return mView;
    }


}
