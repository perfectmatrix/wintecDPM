package com.wintec.degreemap.ui.student.student_courses;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;

import java.util.List;

import static com.wintec.degreemap.util.Constants.PATHWAY_DATABASE_ARCHITECTURE;
import static com.wintec.degreemap.util.Constants.PATHWAY_NETWORK_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_SOFTWARE_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;

public class StudentCourseAdapter extends RecyclerView.Adapter<StudentCourseAdapter.CourseViewHolder> {
    private List<Course> courseList;
    private String selectedPathway;
    private List<String> completedModules;
    private OnItemClickListener listener;

    public StudentCourseAdapter(String selectedPathway, List<String> completedModules) {
        this.selectedPathway = selectedPathway;
        this.completedModules = completedModules;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        public CardView courseCard;
        public LinearLayout courseCardLayout;
        public TextView courseCodeTextView;
        public TextView courseNameTextView;

        public CourseViewHolder(View itemView) {
            super(itemView);
            courseCard = itemView.findViewById(R.id.courseCard);
            courseCardLayout = itemView.findViewById(R.id.courseCardLayout);
            courseCodeTextView = itemView.findViewById(R.id.courseCode);
            courseNameTextView = itemView.findViewById(R.id.courseLongName);
            itemView.setOnClickListener(clickListener);
        }

        public View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        };
    }

    public void setCourses(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    public void setCompletedModules(List<String> completedModules) {
        this.completedModules = completedModules;
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
        Course course = courseList.get(position);
        holder.courseCodeTextView.setText(course.getCode());
        holder.courseNameTextView.setText(course.getLongName());

        // Default to true for courses without pre-requisite
        boolean isPreRequisiteCompleted = true;
        if (!course.getPreRequisite().isEmpty()) {
            for (String courseCode : course.getPreRequisite()) {
                boolean isCompleted = completedModules.contains(courseCode);
                if (!isCompleted) {
                    isPreRequisiteCompleted = false;
                }
            }
        }

        switch (selectedPathway) {
            case PATHWAY_NETWORK_ENGINEERING:
                holder.courseCardLayout.setBackgroundResource(isPreRequisiteCompleted ? R.drawable.bg_course_item_purple : R.drawable.bg_course_item_purple_disabled);
                break;
            case PATHWAY_WEB_DEVELOPMENT:
                holder.courseCardLayout.setBackgroundResource(isPreRequisiteCompleted ? R.drawable.bg_course_item_red : R.drawable.bg_course_item_red_disabled);
                break;
            case PATHWAY_DATABASE_ARCHITECTURE:
                holder.courseCardLayout.setBackgroundResource(isPreRequisiteCompleted ? R.drawable.bg_course_item_green : R.drawable.bg_course_item_green_disabled);
                break;
            case PATHWAY_SOFTWARE_ENGINEERING:
                holder.courseCardLayout.setBackgroundResource(isPreRequisiteCompleted ? R.drawable.bg_course_item_blue : R.drawable.bg_course_item_blue_disabled);
                break;
        }

        // Show check icon on completed modules
        boolean isModuleCompleted = completedModules.contains(course.getCode());
        if (isModuleCompleted) {
            holder.courseNameTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle, 0);
        } else {
            holder.courseNameTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        }

        if (!isPreRequisiteCompleted) {
            holder.courseCodeTextView.setTextColor(Color.GRAY);
            holder.itemView.setOnClickListener(null);
        } else {
            holder.courseCodeTextView.setTextColor(Color.WHITE);
            holder.itemView.setOnClickListener(holder.clickListener);
        }
    }

    @Override
    public int getItemCount() {
        return courseList == null ? 0 : courseList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}