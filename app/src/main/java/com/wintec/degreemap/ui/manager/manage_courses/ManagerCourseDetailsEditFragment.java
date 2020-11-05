package com.wintec.degreemap.ui.manager.manage_courses;

import android.os.Bundle;
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
import com.wintec.degreemap.databinding.FragmentManagerCourseDetailsEditBinding;

import com.wintec.degreemap.viewmodel.CourseViewModel;

import static com.wintec.degreemap.util.Constants.BUNDLE_COURSE_CODE;

public class ManagerCourseDetailsEditFragment extends Fragment implements View.OnClickListener {
    private FragmentManagerCourseDetailsEditBinding binding;
    Button btnSave, btnCancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manager_course_details_edit, container, false);
        View view = binding.getRoot();

        btnSave = view.findViewById(R.id.btn_courseEdit_save);
        btnCancel = view.findViewById(R.id.btn_courseEdit_cancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        String courseCode = getArguments().getString(BUNDLE_COURSE_CODE);
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseDetails(courseCode).observe(getActivity(), new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                if (course != null) {
                    binding.setCourse(course);
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_courseDetails_edit:
               saveData();
               break;
        }
    }

    public void saveData() {

    }
}