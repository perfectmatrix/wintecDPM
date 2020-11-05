package com.wintec.degreemap.ui.manager.manage_courses;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.viewmodel.CourseViewModel;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.BUNDLE_COURSE_CODE;
import static com.wintec.degreemap.util.Constants.BUNDLE_PATHWAY;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;
import static com.wintec.degreemap.util.Helpers.getPathwayLabel;

public class ManagerCourseDetailsFragment extends Fragment implements View.OnClickListener {
    TextView courseCodeTextView,
            courseLongNameTextView,
            courseLevelTextView,
            courseCreditTextView,
            preRequisiteTextView,
            coRequisiteTextView,
            pathwayTextView,
            courseDescriptionTextView;

    Button btnEdit, btnDelete;

    private Course mSelectedCourse;
    SharedPreferences mPrefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_course_details, container, false);

        courseCodeTextView = view.findViewById(R.id.courseCodeTextView);
        courseLongNameTextView = view.findViewById(R.id.courseLongNameTextView);
        courseLevelTextView = view.findViewById(R.id.courseLevelTextView);
        courseCreditTextView = view.findViewById(R.id.courseCreditTextView);
        preRequisiteTextView = view.findViewById(R.id.preRequisiteTextView);
        coRequisiteTextView = view.findViewById(R.id.coRequisiteTextView);
        pathwayTextView = view.findViewById(R.id.pathwayTextView);
        courseDescriptionTextView = view.findViewById(R.id.courseDescriptionTextView);
        btnEdit = view.findViewById(R.id.btn_courseDetails_edit);
        btnDelete = view.findViewById(R.id.btn_courseDetails_delete);

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        mPrefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String courseCode = getArguments().getString(BUNDLE_COURSE_CODE);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseDetails(courseCode).observe(getActivity(), new Observer<Course>() {
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
        pathwayTextView.setText(getPathwayLabel(mSelectedCourse.getPathway()));
        courseDescriptionTextView.setText(mSelectedCourse.getDescription());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_courseDetails_edit:
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_COURSE_CODE, mSelectedCourse.getCode());

                // navigate to course edit fragment
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_managerCourseDetailsFragment_to_managerCourseDetailsEditFragment, bundle);
                break;
        }
    }
}