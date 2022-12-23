package com.example.jobfinderapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AppliedJobAdapter extends RecyclerView.Adapter<AppliedJobAdapter.ViewHolder> {
    private List mJobs;
    private Context mContext;
    private OnItemClickListener listener;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("User");
    private String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public AppliedJobAdapter(List _Jobs, Context mContext) {
        this.mJobs = _Jobs;
        this.mContext = mContext;
    }
    public void setOnItemClickListener(OnItemClickListener clickListener) {
        listener = clickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View jobView =
                inflater.inflate(R.layout.item_applied_job, parent, false);

        ViewHolder viewHolder = new ViewHolder(jobView, listener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Job job = (Job) mJobs.get(position);
        holder.tvAppliedJobName.setText(job.getJobName());
        holder.tvAppliedJobCompany.setText(job.getJobCompany());
        holder.tvAppliedJobType.setText(job.getJobType());
        holder.tvAppliedJobSalary.setText(job.getJobSalary());
        holder.tvAppliedJobLocation.setText(job.getJobLocation());
        if (holder.tvAppliedJobCompany.getText().equals("Tumblr")) {
            holder.ivAppliedJobLogo.setImageResource(R.drawable.tumblr_logo);
        }
        if(holder.tvAppliedJobCompany.getText().equals("Youtube")) {
            holder.ivAppliedJobLogo.setImageResource(R.drawable.youtube_logo);
        }
        if(holder.tvAppliedJobCompany.getText().equals("Facebook")) {
            holder.ivAppliedJobLogo.setImageResource(R.drawable.facebook_logo);
        }
        if(holder.tvAppliedJobCompany.getText().equals("Discord")) {
            holder.ivAppliedJobLogo.setImageResource(R.drawable.discord_logo);
        }
        if(holder.tvAppliedJobCompany.getText().equals("Spotify")) {
            holder.ivAppliedJobLogo.setImageResource(R.drawable.spotify_logo);
        }
        if(holder.tvAppliedJobCompany.getText().equals("Twitter")) {
            holder.ivAppliedJobLogo.setImageResource(R.drawable.twitter_logo);
        }
        holder.ibAppliedJobDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.getAdapterPosition());
                Toast.makeText(mContext.getApplicationContext(), job.getJobId(),Toast.LENGTH_SHORT).show();
                userRef.document(userID).update("jobApplied", FieldValue.arrayRemove(job.getJobId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View item_view;
        TextView tvAppliedJobName, tvAppliedJobCompany, tvAppliedJobSalary, tvAppliedJobLocation, tvAppliedJobType;
        ImageView ivAppliedJobLogo;
        ImageButton ibAppliedJobDelete;
        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            item_view = itemView;
            tvAppliedJobName = itemView.findViewById(R.id.tvAppliedJobName);
            tvAppliedJobCompany = itemView.findViewById(R.id.tvAppliedJobCompany);
            tvAppliedJobLocation = itemView.findViewById(R.id.tvAppliedJobLocation);
            tvAppliedJobSalary = itemView.findViewById(R.id.tvAppliedJobSalary);
            tvAppliedJobType = itemView.findViewById(R.id.tvAppliedJobType);
            ivAppliedJobLogo = itemView.findViewById(R.id.ivAppliedJobLogo);
            ibAppliedJobDelete = itemView.findViewById(R.id.ibAppliedJobDelete);

        }


    }


}
