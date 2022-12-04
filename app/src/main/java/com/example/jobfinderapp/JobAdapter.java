package com.example.jobfinderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter {
    //Dữ liệu hiện thị là danh sách sinh viên
    private List mJobs;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;
    public JobAdapter(List _Jobs, Context mContext) {
        this.mJobs = _Jobs;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View studentView =
                inflater.inflate(R.layout.item_job, parent, false);

        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Job job = (Job) mJobs.get(position);

    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        private TextView tvJobName, tvJobCompany, tvJobSalary, tvJobLocation, tvJobStyle;

//        public TextView studentname;
//        public TextView birthyear;
//        public Button detail_button;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
//            studentname = itemView.findViewById(R.id.studentname);
//            birthyear = itemView.findViewById(R.id.birthyear);
//            detail_button = itemView.findViewById(R.id.detail_button);

            //Xử lý khi nút Chi tiết được bấm
//            detail_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(view.getContext(),
//                                    studentname.getText() +" | "
//                                            + " Demo function", Toast.LENGTH_SHORT)
//                            .show();
//                }
//            });
        }
    }


}
