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

import static com.wintec.degreemap.data.model.Course.PATHWAY_DATABASE_ARCHITECTURE;
import static com.wintec.degreemap.data.model.Course.PATHWAY_NETWORK_ENGINEERING;
import static com.wintec.degreemap.data.model.Course.PATHWAY_SOFTWARE_ENGINEERING;
import static com.wintec.degreemap.data.model.Course.PATHWAY_WEB_DEVELOPMENT;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> implements View.OnClickListener {
    public static final String BUNDLE_COURSE_ID = "BundleCourseId";
    private List<Course> mCourseList;
    private Course mSelectedCourse;
    private String mSelectedPathway;
    private List<String> mCompletedModules;

    public CourseAdapter(String selectedPathway, List<String> completedModules) {
        super();
        mSelectedPathway = selectedPathway;
        mCompletedModules = completedModules;
    }

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

        switch (mSelectedPathway) {
            case PATHWAY_NETWORK_ENGINEERING:
                holder.courseCodeTextView.setBackgroundResource(R.color.lightPurple);
                break;
            case PATHWAY_WEB_DEVELOPMENT:
                holder.courseCodeTextView.setBackgroundResource(R.color.lightBlue);
                break;
            case PATHWAY_DATABASE_ARCHITECTURE:
                holder.courseCodeTextView.setBackgroundResource(R.color.lightGreen);
                break;
            case PATHWAY_SOFTWARE_ENGINEERING:
                holder.courseCodeTextView.setBackgroundResource(R.color.lightRed);
                break;
        }

        // Show check icon on completed modules
        boolean isModuleCompleted = mCompletedModules.contains(mSelectedCourse.getKey());
        if (isModuleCompleted) {
            holder.courseNameTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_check_box,0);
        }

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