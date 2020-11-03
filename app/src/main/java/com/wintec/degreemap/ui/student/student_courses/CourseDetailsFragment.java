package com.wintec.degreemap.ui.student.student_courses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.viewmodel.CourseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.BUNDLE_COURSE_ID;
import static com.wintec.degreemap.util.Constants.KEY_COMPLETED_MODULES;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;
import static com.wintec.degreemap.util.Helpers.getCompletedModules;
import static com.wintec.degreemap.util.Helpers.getPathwayLabel;

public class CourseDetailsFragment extends Fragment {
    TextView courseCodeTextView,
            courseLongNameTextView,
            courseLevelTextView,
            courseCreditTextView,
            preRequisiteTextView,
            coRequisiteTextView,
            pathwayTextView,
            courseDescriptionTextView;
    Button courseUrlButton, markCourseButton;
    private Course mSelectedCourse;
    private List<String> mCompletedModules;
    SharedPreferences mPrefs;

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
        markCourseButton = view.findViewById(R.id.markCourseButton);

        courseUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCourseUrl();
            }
        });

        markCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markCompleteOrIncomplete();
            }
        });

        mPrefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String courseKey = getArguments().getString(BUNDLE_COURSE_ID);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseDetails(courseKey).observe(getActivity(), new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                if (course != null) {
                    mSelectedCourse = course;
                    populateCourseDetails();
                }
            }
        });

        return view;
    }

    public void populateCourseDetails() {
        courseCodeTextView.setText(mSelectedCourse.getCode());
        courseLongNameTextView.setText(mSelectedCourse.getLongName());
        courseLevelTextView.setText(String.valueOf(mSelectedCourse.getLevel()));
        courseCreditTextView.setText(String.valueOf(mSelectedCourse.getCredit()));
        preRequisiteTextView.setText(mSelectedCourse.getPreRequisite().isEmpty()
                ? "None"
                : mSelectedCourse.getPreRequisite().toString()
                        .replace("[", "")
                        .replace("]", ""));
        coRequisiteTextView.setText(mSelectedCourse.getCoRequisite().isEmpty()
                ? "None"
                : mSelectedCourse.getCoRequisite().toString()
                .replace("[", "")
                .replace("]", ""));
        pathwayTextView.setText(getPathwayLabel(mSelectedCourse.getType()));
        courseDescriptionTextView.setText(mSelectedCourse.getDescription());

        mCompletedModules = getCompletedModules(mPrefs);
        setMarkButtonText();
    }

    public void markCompleteOrIncomplete() {
        // Mark modules as complete or incomplete
        if (isModuleCompleted()) {
            mCompletedModules.remove(mCompletedModules.indexOf(mSelectedCourse.getCode()));
        } else {
            mCompletedModules.add(mSelectedCourse.getCode());
        }

        setMarkButtonText();
        saveCompletedModules();
    }

    private void openCourseUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(mSelectedCourse.getUrl()));
        startActivity(intent);
    }

    private void saveCompletedModules() {
        // Save completed modules on SharedPreferences
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(KEY_COMPLETED_MODULES, TextUtils.join(",", mCompletedModules));
        editor.apply();
    }

    private boolean isModuleCompleted() {
        return (mCompletedModules.contains(mSelectedCourse.getCode()));
    }

    private void setMarkButtonText() {
        if (isModuleCompleted()) {
            markCourseButton.setText("Mark as Incomplete");
        } else {
            markCourseButton.setText("Mark as Completed");
        }
    }
}