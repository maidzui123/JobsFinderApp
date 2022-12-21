package com.example.jobfinderapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private List mJobs;
    private Context mContext;
    public JobAdapter(List _Jobs, Context mContext) {
        this.mJobs = _Jobs;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View jobView =
                inflater.inflate(R.layout.item_job, parent, false);

        ViewHolder viewHolder = new ViewHolder(jobView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Job job = (Job) mJobs.get(position);
        holder.tvJobName.setText(job.getJobName());
        holder.tvJobCompany.setText(job.getJobCompany());
        holder.tvJobType.setText(job.getJobType());
        holder.tvJobSalary.setText(job.getJobSalary());
        holder.tvJobLocation.setText(job.getJobLocation());
        if (holder.tvJobCompany.getText().equals("Tumblr")) {
            holder.ivJobLogo.setImageResource(R.drawable.tumblr_logo);
        }
        if(holder.tvJobCompany.getText().equals("Youtube")) {
            holder.ivJobLogo.setImageResource(R.drawable.youtube_logo);
        }
        if(holder.tvJobCompany.getText().equals("Facebook")) {
            holder.ivJobLogo.setImageResource(R.drawable.facebook_logo);
        }
        if(holder.tvJobCompany.getText().equals("Discord")) {
            holder.ivJobLogo.setImageResource(R.drawable.discord_logo);
        }
        if(holder.tvJobCompany.getText().equals("Spotify")) {
            holder.ivJobLogo.setImageResource(R.drawable.spotify_logo);
        }
        if(holder.tvJobCompany.getText().equals("Twitter")) {
            holder.ivJobLogo.setImageResource(R.drawable.twitter_logo);
        }
        holder.lnSingleJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), SingleJobActivity.class);
                intent.putExtra("strSingleJobId", job.getJobId());
                intent.putExtra("tvSingleJobName", job.getJobName());
                intent.putExtra("tvSingleJobCompany", job.getJobCompany());
                intent.putExtra("tvSingleJobField", job.getJobField());
                intent.putExtra("tvSingleJobLocation", job.getJobLocation());
                intent.putExtra("tvSingleJobType", job.getJobType());
                intent.putExtra("tvSingleJobSalary", job.getJobSalary());
                intent.putExtra("tvSingleJobField", job.getJobField());
                intent.putExtra("tvSingleJobRq1", job.getJobRq1());
                intent.putExtra("tvSingleJobRq2", job.getJobRq2());
                intent.putExtra("tvSingleJobRq3", job.getJobRq3());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View item_view;
        TextView tvJobName, tvJobCompany, tvJobSalary, tvJobLocation, tvJobType;
        LinearLayout lnSingleJob;
        ImageView ivJobLogo;
        public ViewHolder(View itemView) {
            super(itemView);
            item_view = itemView;
            tvJobName = itemView.findViewById(R.id.tvJobName);
            tvJobCompany = itemView.findViewById(R.id.tvJobCompany);
            tvJobLocation = itemView.findViewById(R.id.tvJobLocation);
            tvJobSalary = itemView.findViewById(R.id.tvJobSalary);
            tvJobType = itemView.findViewById(R.id.tvJobType);
            ivJobLogo = itemView.findViewById(R.id.ivJobLogo);
            lnSingleJob = itemView.findViewById(R.id.lnSingleJob);

        }
    }


}
