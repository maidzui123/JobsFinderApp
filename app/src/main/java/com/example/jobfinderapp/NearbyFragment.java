package com.example.jobfinderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class NearbyFragment extends Fragment {



    public NearbyFragment() {
        // Required empty public constructor
    }

    String[] job_items = {"Programming", "Marketing", "Design", "Healthcare"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_nearby, container, false);
        autoCompleteTxt = mView.findViewById(R.id.autoCompleteTxt);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.job_dropdown_item, job_items);
        autoCompleteTxt.setAdapter(adapterItems);
        return mView;
    }
}