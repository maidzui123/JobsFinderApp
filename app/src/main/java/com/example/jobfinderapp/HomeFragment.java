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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        ibHomeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new BottomFragment());
            }
        });
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseFirestore.collection("Jobs").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    if(customProgressDialog.isShowing())
                        customProgressDialog.dismiss();
                    Log.e("Database error", e.getMessage());
                    return;
                }

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Job job = documentSnapshot.toObject(Job.class);
                    job.setJobId(documentSnapshot.getId());
                    jobs.add(job);
                }
                adapter.notifyDataSetChanged();
                if(customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flHomeFragment, fragment);
        fragmentTransaction.commit();
    }
}


