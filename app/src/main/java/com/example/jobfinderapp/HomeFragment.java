package com.example.jobfinderapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }
    private ImageButton ibHomeFilter;
    private View mView;

    private RecyclerView rvSuggestedJob;
    private JobAdapter adapter;
    private FirebaseFirestore firebaseFirestore;

    private CustomProgressDialog customProgressDialog;

    ArrayList<Job> jobs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        ibHomeFilter = mView.findViewById(R.id.ibHomeFilter);
        customProgressDialog = new CustomProgressDialog(getContext());
        rvSuggestedJob = mView.findViewById(R.id.rvSuggestedJob);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvSuggestedJob.setLayoutManager(linearLayoutManager);
        rvSuggestedJob.setHasFixedSize(true);
        customProgressDialog.show();
        firebaseFirestore = FirebaseFirestore.getInstance();
        jobs = new ArrayList<Job>();
        adapter = new JobAdapter(jobs, getContext());
        rvSuggestedJob.setAdapter(adapter);

        EventChangeListener();

        ibHomeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new BottomFragment());
            }
        });
        return mView;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        jobs = new ArrayList<Job>();
////        dataInitialize();
//        adapter = new JobAdapter(jobs, getContext());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        rvSuggestedJob.setLayoutManager(linearLayoutManager);
//        rvSuggestedJob.setHasFixedSize(true);
//        rvSuggestedJob.setAdapter(adapter);
//        firebaseFirestore.collection("Jobs").orderBy("jobName", Query.Direction.ASCENDING)
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        if(error != null) {
//                            Log.e("Database error", error.getMessage());
//                            return;
//                        }
//                        for (DocumentChange dc : value.getDocumentChanges()) {
//                            if (dc.getType() == DocumentChange.Type.ADDED) {
//                                jobs.add(dc.getDocument().toObject(Job.class));
//                            }
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//                });
////        EventChangeListener();
//    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flHomeFragment, fragment);
        fragmentTransaction.commit();
    }
//    private void dataInitialize() {
//
//        String[] name = {"IT Support","IT Support","IT Support","IT Support","IT Support","IT Support"};
//        String[] company = {"Tumblr","Youtube","Facebook","Discord","Spotify","Twitter"};
//        String[] salary = {"$1500/Mo","$1500/Mo","$1500/Mo","$1500/Mo","$1500/Mo","$1500/Mo"};
//        String[] address = {"Ha Noi","Ha Noi","Ha Noi","Ha Noi","Ha Noi","Ha Noi"};
//        String[] type = {"Full-time","Full-time","Full-time","Full-time","Full-time","Full-time"};
//        for(int i = 0; i < name.length; i++) {
//            Job job = new Job(name[i], company[i], salary[i], address[i], type[i]);
//            jobs.add(job);
//        }
//
//    }
    private void EventChangeListener() {
        firebaseFirestore.collection("Jobs").orderBy("jobSalary", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null) {
                            if(customProgressDialog.isShowing())
                                customProgressDialog.dismiss();
                            Log.e("Database error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                jobs.add(dc.getDocument().toObject(Job.class));
                            }
                            adapter.notifyDataSetChanged();
                            if(customProgressDialog.isShowing())
                                customProgressDialog.dismiss();
                        }
                    }
                });
    }

//    private void showDialog() {
//        final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.frame_bottom_sheet_dialog);
//        dialog.show();
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
//    }
}

