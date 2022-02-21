package com.arbaz.pusiapp.ui.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaz.pusiapp.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.viewHolder> {


    ArrayList<CourseModel> list;
    Context context;

    public CourseAdapter(ArrayList<CourseModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_courses,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final CourseModel courseModel = list.get(position);

        holder.courseimg.setImageResource(courseModel.getImage());
        holder.coursetitle.setText(courseModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    protected class viewHolder extends RecyclerView.ViewHolder {
        ImageView courseimg;
        TextView coursetitle;

        public viewHolder(View itemview) {
            super(itemview);

            courseimg = itemview.findViewById(R.id.course_img);
            coursetitle = itemview.findViewById(R.id.course_title);
        }
    }
}
