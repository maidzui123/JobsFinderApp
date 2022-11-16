package com.example.jobfinderapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {
    public BottomSheet() {
    }
    String[] job_items = {"Programming", "Marketing", "Design", "Healthcare"};
    AutoCompleteTextView autoCompleteTxt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.bottom_sheet_dialog,container, false);
        autoCompleteTxt = mView.findViewById(R.id.autoCompleteTxt);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.job_dropdown_item, job_items);
        autoCompleteTxt.setAdapter(adapterItems);
//        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getActivity(), "Item: " + item, Toast.LENGTH_SHORT).show();
//            }
//        });
        return mView;
    }
}
