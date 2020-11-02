package com.wintec.degreemap.ui.student.student_courses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.ui.student.student_dashboard.DashboardFragment;

public class CourseDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_details, container, false);

        String courseKey = getArguments().getString(CourseAdapter.BUNDLE_COURSE_ID);

        return view;
    }
}