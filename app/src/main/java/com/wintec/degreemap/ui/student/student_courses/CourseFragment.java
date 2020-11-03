package com.wintec.degreemap.ui.student.student_courses;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.ui.student.student_dashboard.DashboardFragment;
import com.wintec.degreemap.viewmodel.CourseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.ALL_COURSE;
import static com.wintec.degreemap.util.Constants.BUNDLE_COURSE_ID;
import static com.wintec.degreemap.util.Constants.FIRST_YEAR;
import static com.wintec.degreemap.util.Constants.KEY_COMPLETED_MODULES;
import static com.wintec.degreemap.util.Constants.KEY_SELECTED_PATHWAY;
import static com.wintec.degreemap.util.Constants.PATHWAY_CORE;
import static com.wintec.degreemap.util.Constants.SECOND_YEAR;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;
import static com.wintec.degreemap.util.Constants.THIRD_YEAR;
import static com.wintec.degreemap.util.Helpers.getCompletedModules;
import static com.wintec.degreemap.util.Helpers.getPathwayLabel;

public class CourseFragment extends Fragment implements AdapterView.OnItemSelectedListener, CourseAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private CourseAdapter mCourseAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String mSelectedPathway;
    private List<Course> mFilteredCourseList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mSelectedPathway.isEmpty()) {
            Navigation.findNavController(view).navigate(R.id.dashboardFragment);
            Toast.makeText(getActivity().getApplicationContext(), "Select a pathway first", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        Spinner spinner = view.findViewById(R.id.spinner_year);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.chooseYear, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // get selected pathway
        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        mSelectedPathway = prefs.getString(KEY_SELECTED_PATHWAY, "");

        List<String> completedModules = getCompletedModules(prefs);
        mCourseAdapter = new CourseAdapter(mSelectedPathway, completedModules);

        // set recyclerView data
        mRecyclerView = view.findViewById(R.id.recyclerview_all_courses);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCourseAdapter);
        mCourseAdapter.setOnItemClickListener(this);

        // set pathway title and background color
        setPathwayTextViewFormatting(view, mSelectedPathway);

        return view;
    }

    private void setPathwayTextViewFormatting(View view, String pathway) {
        TextView pathwayTextView = view.findViewById(R.id.pathwayTextView);
        pathwayTextView.setText(getPathwayLabel(pathway));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        // get all course data
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseList().observe(getActivity(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courseList) {
                if (courseList != null) {
                    mFilteredCourseList = new ArrayList<>();

                    switch (position) {
                        case ALL_COURSE: {
                            for (Course course : courseList) {
                                if (course.getType().equalsIgnoreCase(mSelectedPathway) || course.getType().equalsIgnoreCase(PATHWAY_CORE))
                                    mFilteredCourseList.add(course);
                            }
                            break;
                        }
                        case FIRST_YEAR:
                        case SECOND_YEAR:
                        case THIRD_YEAR: {
                            for (Course course : courseList) {
                                if (course.getYear() == position && (course.getType().equalsIgnoreCase(mSelectedPathway) || course.getType().equalsIgnoreCase(PATHWAY_CORE)))
                                    mFilteredCourseList.add(course);
                            }
                            break;
                        }
                    }
                    mCourseAdapter.setCourses(mFilteredCourseList);
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_COURSE_ID, mFilteredCourseList.get(position).getCode());

        // navigate to course details fragment
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_courseFragment_to_courseDetailsFragment, bundle);
    }
}
