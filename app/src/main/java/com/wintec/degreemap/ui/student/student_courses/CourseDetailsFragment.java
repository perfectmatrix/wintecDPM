package com.wintec.degreemap.ui.student.student_courses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.ui.student.student_dashboard.DashboardFragment;
import com.wintec.degreemap.viewmodel.CourseViewModel;

public class CourseDetailsFragment extends Fragment {
    TextView courseCodeTextView,
            courseLongNameTextView,
            courseLevelTextView,
            courseCreditTextView,
            preRequisiteTextView ,
            coRequisiteTextView,
            pathwayTextView,
            courseDescriptionTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_details, container, false);

        courseCodeTextView = view.findViewById(R.id.courseCodeTextView);
        courseLongNameTextView = view.findViewById(R.id.courseLongNameTextView);
        courseLevelTextView = view.findViewById(R.id.courseLevelTextView);
        courseCreditTextView = view.findViewById(R.id.courseCreditTextView);
        preRequisiteTextView = view.findViewById(R.id.preRequisiteTextView);
        coRequisiteTextView = view.findViewById(R.id.coRequisiteTextView);
        pathwayTextView = view.findViewById(R.id.pathwayTextView);
        courseDescriptionTextView = view.findViewById(R.id.courseDescriptionTextView);

        String courseKey = getArguments().getString(CourseAdapter.BUNDLE_COURSE_ID);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseDetails(courseKey).observe(getActivity(), new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                if(course != null) {
                    populateCourseDetails(course);
                }
            }
        });

        return view;
    }

    public void populateCourseDetails(Course course) {
        courseCodeTextView.setText(course.getCode());
        courseLongNameTextView.setText(course.getLongName());
        courseLevelTextView.setText(String.valueOf(course.getLevel()));
        courseCreditTextView.setText(String.valueOf(course.getCredit()));
        preRequisiteTextView.setText("Pre-requisite");
        coRequisiteTextView.setText("Co-requisite");
        pathwayTextView.setText(Course.getPathwayLabel(course.getType()));
        courseDescriptionTextView.setText(course.getDescription());
    }
}