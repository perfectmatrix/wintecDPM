package com.wintec.degreemap.ui.manager.manage_courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.databinding.FragmentManagerCourseFormBinding;
import com.wintec.degreemap.util.Constants;
import com.wintec.degreemap.viewmodel.CourseViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.wintec.degreemap.util.Constants.BUNDLE_COURSE_CODE;
import static com.wintec.degreemap.util.StringUtils.convertToArrayList;

public class ManagerCourseFormFragment extends Fragment implements View.OnClickListener {
    private FragmentManagerCourseFormBinding binding;
    private MultiAutoCompleteTextView preRequisiteTextView, coRequisiteTextView;
    private AutoCompleteCourseAdapter autoCompleteCourseAdapter;
    private Button btnSave, btnCancel;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manager_course_form, container, false);
        view = binding.getRoot();

        preRequisiteTextView = view.findViewById(R.id.preRequisiteAutocomplete);
        coRequisiteTextView = view.findViewById(R.id.coRequisiteAutocomplete);
        btnSave = view.findViewById(R.id.btn_courseEdit_save);
        btnCancel = view.findViewById(R.id.btn_courseEdit_cancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        String courseCode = getArguments().getString(BUNDLE_COURSE_CODE);
        if (courseCode.isEmpty())
            setEmptyCourse();
        else {
            courseViewModel.getCourseDetails(courseCode).observe(getViewLifecycleOwner(), new Observer<Course>() {
                @Override
                public void onChanged(Course course) {
                    if (course != null) {
                        binding.setCourse(course);
                        binding.setIsPathwayNetwork(course.getPathway().contains(Constants.PATHWAY_NETWORK_ENGINEERING));
                        binding.setIsPathwayWeb(course.getPathway().contains(Constants.PATHWAY_WEB_DEVELOPMENT));
                        binding.setIsPathwayDatabase(course.getPathway().contains(Constants.PATHWAY_DATABASE_ARCHITECTURE));
                        binding.setIsPathwaySoftware(course.getPathway().contains(Constants.PATHWAY_SOFTWARE_ENGINEERING));
                    } else {
                        setEmptyCourse();
                    }
                }
            });
        }

        autoCompleteCourseAdapter = new AutoCompleteCourseAdapter(getContext());
        preRequisiteTextView.setAdapter(autoCompleteCourseAdapter);
        coRequisiteTextView.setAdapter(autoCompleteCourseAdapter);
        preRequisiteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        coRequisiteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        // Setup pre-requisites and co-requisites auto complete
        courseViewModel.getCourseList().observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courseList) {
                if (courseList != null) {
                    autoCompleteCourseAdapter.setCourses(courseList);
                }
            }
        });

        return view;
    }

    private void setEmptyCourse() {
        Course course = new Course("",
                new ArrayList<String>(),
                0,
                "",
                "",
                0,
                new ArrayList<String>(),
                0, new ArrayList<String>(),
                "", 1);
        binding.setCourse(course);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_courseEdit_save:
                saveData();
                break;
            case R.id.btn_courseEdit_cancel:
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_COURSE_CODE, binding.getCourse().getCode());

                NavHostFragment.findNavController(this).navigate(R.id.action_managerCourseFormFragment_to_managerCourseDetailsFragment, bundle);
                break;
        }
    }

    public void saveData() {
        Course course = new Course(null,
                null,
                binding.getCourse().getCredit(),
                binding.getCourse().getDescription(),
                binding.getCourse().getLongName(),
                binding.getCourse().getLevel(),
                null,
                binding.getCourse().getSemester(),
                null,
                binding.getCourse().getUrl(),
                binding.getCourse().getYear());

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        courseViewModel.saveCourse(binding.getCourse().getCode(),
                course,
                getPathways(),
                convertToArrayList(preRequisiteTextView.getText().toString()),
                convertToArrayList(coRequisiteTextView.getText().toString()));

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_COURSE_CODE, binding.getCourse().getCode());

        NavHostFragment.findNavController(this).navigate(R.id.action_managerCourseFormFragment_to_managerCourseDetailsFragment, bundle);
    }

    private ArrayList<String> getPathways() {
        ArrayList<String> pathways = new ArrayList<>();

        // If none of the checkbox is selected, then the pathway is core
        // otherwise, check each pathway selected
        if (!binding.getIsPathwayWeb() &&
                !binding.getIsPathwayNetwork() &&
                !binding.getIsPathwayDatabase() &&
                !binding.getIsPathwaySoftware()) {
            pathways.add(Constants.PATHWAY_CORE);
        } else {
            if (binding.getIsPathwayNetwork()) {
                pathways.add(Constants.PATHWAY_NETWORK_ENGINEERING);
            }

            if (binding.getIsPathwayWeb()) {
                pathways.add(Constants.PATHWAY_WEB_DEVELOPMENT);
            }

            if (binding.getIsPathwayDatabase()) {
                pathways.add(Constants.PATHWAY_DATABASE_ARCHITECTURE);
            }

            if (binding.getIsPathwaySoftware()) {
                pathways.add(Constants.PATHWAY_SOFTWARE_ENGINEERING);
            }
        }

        return pathways;
    }
}