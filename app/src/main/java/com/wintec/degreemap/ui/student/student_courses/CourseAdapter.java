package com.wintec.degreemap.ui.student.student_courses;

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

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> mCourseList;
    private Course mSelectedCourse;
    private String mSelectedPathway;
    private List<String> mCompletedModules;
    private OnItemClickListener mListener;

    public CourseAdapter(String selectedPathway, List<String> completedModules) {
        super();
        mSelectedPathway = selectedPathway;
        mCompletedModules = completedModules;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
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

        // Default to true for courses without pre-requisite
        boolean isPreRequisiteCompleted = true;
        if(!mSelectedCourse.getPreRequisite().isEmpty()) {
            for (String courseKey : mSelectedCourse.getPreRequisite()) {
                boolean isCompleted = mCompletedModules.contains(courseKey);
                if (!isCompleted) {
                    isPreRequisiteCompleted = false;
                }
            }
        }


        switch (mSelectedPathway) {
            case PATHWAY_NETWORK_ENGINEERING:
                holder.courseCardLayout.setBackgroundResource(isPreRequisiteCompleted ? R.drawable.bg_course_item_purple : R.drawable.bg_course_item_purple_disabled);
                break;
            case PATHWAY_WEB_DEVELOPMENT:
                holder.courseCardLayout.setBackgroundResource(isPreRequisiteCompleted ? R.drawable.bg_course_item_blue : R.drawable.bg_course_item_blue_disabled);
                break;
            case PATHWAY_DATABASE_ARCHITECTURE:
                holder.courseCardLayout.setBackgroundResource(isPreRequisiteCompleted ? R.drawable.bg_course_item_green : R.drawable.bg_course_item_green_disabled);
                break;
            case PATHWAY_SOFTWARE_ENGINEERING:
                holder.courseCardLayout.setBackgroundResource(isPreRequisiteCompleted ? R.drawable.bg_course_item_red : R.drawable.bg_course_item_red_disabled);
                break;
        }

        // Show check icon on completed modules
        boolean isModuleCompleted = mCompletedModules.contains(mSelectedCourse.getKey());
        if (isModuleCompleted) {
            holder.courseNameTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check_box, 0);
        }

        if (!isPreRequisiteCompleted)
            holder.itemView.setOnClickListener(null);
    }

    @Override
    public int getItemCount() {
        return mCourseList == null ? 0 : mCourseList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}