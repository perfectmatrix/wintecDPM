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

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.databinding.FragmentStudentCourseDetailsBinding;
import com.wintec.degreemap.viewmodel.CourseViewModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.BUNDLE_COURSE_CODE;
import static com.wintec.degreemap.util.Constants.KEY_COMPLETED_MODULES;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;
import static com.wintec.degreemap.util.Helpers.getCompletedModules;

public class StudentCourseDetailsFragment extends Fragment implements View.OnClickListener {
    private FragmentStudentCourseDetailsBinding binding;
    private Button courseUrlButton, markCourseButton;
    private List<String> completedModules;
    private SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_course_details, container, false);
        View view = binding.getRoot();

        courseUrlButton = view.findViewById(R.id.courseUrlButton);
        markCourseButton = view.findViewById(R.id.markCourseButton);

        courseUrlButton.setOnClickListener(this);
        markCourseButton.setOnClickListener(this);

        prefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String courseCode = getArguments().getString(BUNDLE_COURSE_CODE);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseDetails(courseCode).observe(getActivity(), new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                if (course != null) {
                    binding.setCourse(course);
                    completedModules = getCompletedModules(prefs);
                    setIsModuleCompleted();
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.markCourseButton:
                markCompleteOrIncomplete();
                break;
            case R.id.courseUrlButton:
                openCourseUrl();
                break;
        }
    }

    private void openCourseUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(binding.getCourse().getUrl()));
        startActivity(intent);
    }

    private void markCompleteOrIncomplete() {
        if (binding.getIsModuleCompleted()) {
            completedModules.remove(completedModules.indexOf(binding.getCourse().getCode()));
        } else {
            completedModules.add(binding.getCourse().getCode());
        }

        saveCompletedModules();
        setIsModuleCompleted();
    }

    private void saveCompletedModules() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_COMPLETED_MODULES, TextUtils.join(",", completedModules));
        editor.apply();
    }

    private void setIsModuleCompleted() {
        binding.setIsModuleCompleted(completedModules.contains(binding.getCourse().getCode()));
    }
}