package com.example.jobfinderapp;

import static android.content.Intent.getIntent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class AppliedFragment extends Fragment {
    private View mView;
    private RecyclerView rvAppliedJob;
    private AppliedJobAdapter adapter;
    private TextView tvAppliedJobEmpty;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference jobsRef = db.collection("Jobs");
    private CollectionReference userRef = db.collection("User");
    private String userID;
    private CustomProgressDialog customProgressDialog;
    ArrayList<Job> jobs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_applied, container, false);
        rvAppliedJob = mView.findViewById(R.id.rvAppliedJob);
        tvAppliedJobEmpty = mView.findViewById(R.id.tvAppliedJobEmpty);
        customProgressDialog = new CustomProgressDialog(getContext());
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvAppliedJob.setLayoutManager(linearLayoutManager);
        rvAppliedJob.setHasFixedSize(true);
        customProgressDialog.show();
        jobs = new ArrayList<Job>();
        adapter = new AppliedJobAdapter(jobs, getContext());
        rvAppliedJob.setAdapter(adapter);
        adapter.setOnItemClickListener(new AppliedJobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                jobs.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        userRef.document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                List<String> listAppliedJob = (List<String>) documentSnapshot.get("jobApplied");
                if(listAppliedJob.isEmpty()) {
                    if(customProgressDialog.isShowing())
                        customProgressDialog.dismiss();
                    tvAppliedJobEmpty.setVisibility(View.VISIBLE);
                    return;
                }
                else {
                    for (String appliedJobId : listAppliedJob) {
                        jobsRef.document(appliedJobId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Job job = new Job(appliedJobId,
                                        documentSnapshot.getString("jobName"),
                                        documentSnapshot.getString("jobCompany"),
                                        documentSnapshot.getString("jobSalary"),
                                        documentSnapshot.getString("jobLocation"),
                                        documentSnapshot.getString("jobType"),
                                        documentSnapshot.getString("jobField"),
                                        documentSnapshot.getString("jobRq1"),
                                        documentSnapshot.getString("jobRq2"),
                                        documentSnapshot.getString("jobRq3"));
                                jobs.add(job);
                                tvAppliedJobEmpty.setVisibility(View.INVISIBLE);
                                adapter.notifyDataSetChanged();
                                if (customProgressDialog.isShowing())
                                    customProgressDialog.dismiss();
                            }
                        });
                    }
                }
            }
        });
    }
}
