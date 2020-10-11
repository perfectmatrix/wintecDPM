package com.wintec.degreemap.ui.course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Module;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private ArrayList<Module> mCourseList;

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public TextView mModuleCode;
        public TextView mModuleLongName;

        public CourseViewHolder(View itemView) {
            super(itemView);
            mModuleCode = itemView.findViewById(R.id.moduleCode);
            mModuleLongName = itemView.findViewById(R.id.moduleLongName);
        }
    }

    public CourseAdapter(ArrayList<Module> courseList) {
        mCourseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        CourseViewHolder evh = new CourseViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Module currentItem = mCourseList.get(position);
        holder.mModuleCode.setText(currentItem.getModuleCode());
        holder.mModuleLongName.setText(currentItem.getModuleLongName());
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }
}