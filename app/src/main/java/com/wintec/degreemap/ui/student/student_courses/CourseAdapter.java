package com.wintec.degreemap.ui.student.student_courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> implements View.OnClickListener {
    public static final String BUNDLE_COURSE_ID = "BundleCourseId";
    private List<Course> mCourseList;
    private Course mSelectedCourse;

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        public CardView courseCard;
        public TextView courseCodeTextView;
        public TextView courseNameTextView;

        public CourseViewHolder(View itemView) {
            super(itemView);
            courseCard = itemView.findViewById(R.id.courseCard);
            courseCodeTextView = itemView.findViewById(R.id.courseCode);
            courseNameTextView = itemView.findViewById(R.id.courseLongName);
        }
    }

    public void setCourses(List<Course> courseList) {
        this.mCourseList = courseList;
        notifyDataSetChanged();
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
        mSelectedCourse = mCourseList.get(position);
        holder.courseCodeTextView.setText(mSelectedCourse.getCode());
        holder.courseNameTextView.setText(mSelectedCourse.getLongName());
        holder.courseCard.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mCourseList == null ? 0 : mCourseList.size();
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_COURSE_ID, mSelectedCourse.getKey());

        // navigate to course details fragment
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_courseFragment_to_courseDetailsFragment, bundle);
    }
}