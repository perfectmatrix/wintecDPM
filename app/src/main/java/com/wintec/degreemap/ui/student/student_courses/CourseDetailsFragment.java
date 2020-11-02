package com.wintec.degreemap.ui.student.student_courses;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    Button courseUrlButton;
    private Course mCourse;
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
        courseUrlButton = view.findViewById(R.id.courseUrlButton);

        courseUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCourseUrl();
            }
        });

        String courseKey = getArguments().getString(CourseAdapter.BUNDLE_COURSE_ID);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseDetails(courseKey).observe(getActivity(), new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                if(course != null) {
                    mCourse = course;
                    populateCourseDetails();
                }
            }
        });

        return view;
    }

    public void populateCourseDetails() {
        courseCodeTextView.setText(mCourse.getCode());
        courseLongNameTextView.setText(mCourse.getLongName());
        courseLevelTextView.setText(String.valueOf(mCourse.getLevel()));
        courseCreditTextView.setText(String.valueOf(mCourse.getCredit()));
        preRequisiteTextView.setText("Pre-requisite");
        coRequisiteTextView.setText("Co-requisite");
        pathwayTextView.setText(Course.getPathwayLabel(mCourse.getType()));
        courseDescriptionTextView.setText(mCourse.getDescription());
    }

    public void openCourseUrl()
    {
        Intent intent= new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(mCourse.getUrl()));
        startActivity(intent);
    }
}