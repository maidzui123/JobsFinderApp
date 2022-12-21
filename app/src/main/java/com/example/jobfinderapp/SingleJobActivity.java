package com.example.jobfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SingleJobActivity extends AppCompatActivity {
    private ImageView ivSingleJobLogo;
    private TextView tvSingleJobCompany, tvSingleJobName, tvSingleJobField, tvSingleJobLocation, tvSingleJobType, tvSingleJobRq1, tvSingleJobRq2, tvSingleJobRq3, tvSingleJobSalary;
    private ImageButton ibSingleJobBack;
    private Button btnSingleJobApply;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth;
    private CollectionReference jobsRef = db.collection("Jobs");
    private CollectionReference userRef = db.collection("User");
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_job);
        ivSingleJobLogo = findViewById(R.id.ivSingleJobLogo);
        tvSingleJobCompany = findViewById(R.id.tvSingleJobCompany);
        tvSingleJobName = findViewById(R.id.tvSingleJobName);
        tvSingleJobField = findViewById(R.id.tvSingleJobField);
        tvSingleJobLocation = findViewById(R.id.tvSingleJobLocation);
        tvSingleJobType = findViewById(R.id.tvSingleJobType);
        tvSingleJobRq1 = findViewById(R.id.tvSingleJobRq1);
        tvSingleJobRq2 = findViewById(R.id.tvSingleJobRq2);
        tvSingleJobRq3 = findViewById(R.id.tvSingleJobRq3);
        tvSingleJobSalary = findViewById(R.id.tvSingleJobSalary);
        ibSingleJobBack = findViewById(R.id.ibSingleJobBack);
        btnSingleJobApply = findViewById(R.id.btnSingleJobApply);

        tvSingleJobName.setText(getIntent().getStringExtra("tvSingleJobName"));
        tvSingleJobCompany.setText(getIntent().getStringExtra("tvSingleJobCompany"));
        tvSingleJobField.setText(getIntent().getStringExtra("tvSingleJobField"));
        tvSingleJobLocation.setText(getIntent().getStringExtra("tvSingleJobLocation"));
        tvSingleJobType.setText(getIntent().getStringExtra("tvSingleJobType"));
        tvSingleJobSalary.setText(getIntent().getStringExtra("tvSingleJobSalary"));
        tvSingleJobField.setText(getIntent().getStringExtra("tvSingleJobField"));
        tvSingleJobRq1.setText(getIntent().getStringExtra("tvSingleJobRq1"));
        tvSingleJobRq2.setText(getIntent().getStringExtra("tvSingleJobRq2"));
        tvSingleJobRq3.setText(getIntent().getStringExtra("tvSingleJobRq3"));
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        if (tvSingleJobCompany.getText().equals("Tumblr")) {
            ivSingleJobLogo.setImageResource(R.drawable.tumblr_logo);
        }
        if(tvSingleJobCompany.getText().equals("Youtube")) {
            ivSingleJobLogo.setImageResource(R.drawable.youtube_logo);
        }
        if(tvSingleJobCompany.getText().equals("Facebook")) {
            ivSingleJobLogo.setImageResource(R.drawable.facebook_logo);
        }
        if(tvSingleJobCompany.getText().equals("Discord")) {
            ivSingleJobLogo.setImageResource(R.drawable.discord_logo);
        }
        if(tvSingleJobCompany.getText().equals("Spotify")) {
            ivSingleJobLogo.setImageResource(R.drawable.spotify_logo);
        }
        if(tvSingleJobCompany.getText().equals("Twitter")) {
            ivSingleJobLogo.setImageResource(R.drawable.twitter_logo);
        }

        ibSingleJobBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        btnSingleJobApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateJobApplied(userID);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(),"Apply Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateJobApplied(String userID) {
        String jobId = getIntent().getStringExtra("strSingleJobId");
        userRef.document(userID).update("jobApplied", FieldValue.arrayUnion(jobId));
    }
}