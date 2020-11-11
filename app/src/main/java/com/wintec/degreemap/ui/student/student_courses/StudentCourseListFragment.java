package com.wintec.degreemap.ui.student.student_courses;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.viewmodel.CourseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.ALL_COURSE;
import static com.wintec.degreemap.util.Constants.BUNDLE_COURSE_CODE;
import static com.wintec.degreemap.util.Constants.FIRST_YEAR;
import static com.wintec.degreemap.util.Constants.KEY_COMPLETED_MODULES;
import static com.wintec.degreemap.util.Constants.KEY_SELECTED_PATHWAY;
import static com.wintec.degreemap.util.Constants.PATHWAY_CORE;
import static com.wintec.degreemap.util.Constants.SECOND_YEAR;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;
import static com.wintec.degreemap.util.Constants.THIRD_YEAR;
import static com.wintec.degreemap.util.Helpers.getCompletedModules;
import static com.wintec.degreemap.util.Helpers.getPathwayLabel;

public class StudentCourseListFragment extends Fragment implements AdapterView.OnItemSelectedListener, StudentCourseAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private StudentCourseAdapter studentCourseAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String selectedPathway;
    private List<Course> filteredCourseList;
    private SharedPreferences prefs;
    private List<String> completedModules;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (selectedPathway.isEmpty()) {
            NavHostFragment.findNavController(this).navigate(R.id.studentDashboardFragment);
            Toast.makeText(getContext(), "Select a pathway first", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_course_list, container, false);

        Spinner spinner = view.findViewById(R.id.spinner_year);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.chooseYear, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(FIRST_YEAR);
        spinner.setOnItemSelectedListener(this);

        // get selected pathway
        prefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        selectedPathway = prefs.getString(KEY_SELECTED_PATHWAY, "");

        completedModules = getCompletedModules(prefs);
        studentCourseAdapter = new StudentCourseAdapter(selectedPathway, completedModules);

        // set recyclerView data
        recyclerView = view.findViewById(R.id.recyclerview_all_courses);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentCourseAdapter);
        studentCourseAdapter.setOnItemClickListener(this);

        // set pathway title and background color
        setPathwayTextViewFormatting(view, selectedPathway);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT);
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            markCompleteOrIncomplete(filteredCourseList.get(position).getCode());
            studentCourseAdapter.notifyItemChanged(position);
            studentCourseAdapter.notifyDataSetChanged();
        }


        @Override
        public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            int position = viewHolder.getAdapterPosition();
            boolean isModuleCompleted = completedModules.contains(filteredCourseList.get(position).getCode());

            new RecyclerViewSwipeDecorator.Builder(getContext(), canvas, recyclerView, viewHolder, dX + 20, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(getContext().getColor(R.color.backgroundColor))
                    .addActionIcon(isModuleCompleted ? R.drawable.ic_cancel : R.drawable.ic_check_box)
                    .create()
                    .decorate();

            super.onChildDraw(canvas, recyclerView, viewHolder, dX / 3, dY, actionState, isCurrentlyActive);
        }
    };

    private void setPathwayTextViewFormatting(View view, String pathway) {
        TextView pathwayTextView = view.findViewById(R.id.pathwayTextView);
        pathwayTextView.setText(getPathwayLabel(new ArrayList<>(Arrays.asList(pathway))));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
        // get all course data
        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseList().observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courseList) {
                if (courseList != null) {
                    filteredCourseList = new ArrayList<>();

                    switch (position) {
                        case ALL_COURSE: {
                            for (Course course : courseList) {
                                if (course.getPathway().contains(selectedPathway) || course.getPathway().contains(PATHWAY_CORE))
                                    filteredCourseList.add(course);
                            }
                            break;
                        }
                        case FIRST_YEAR:
                        case SECOND_YEAR:
                        case THIRD_YEAR: {
                            for (Course course : courseList) {
                                if (course.getYear() == position && (course.getPathway().contains(selectedPathway) || course.getPathway().contains(PATHWAY_CORE)))
                                    filteredCourseList.add(course);
                            }
                            break;
                        }
                    }
                    studentCourseAdapter.setCourses(filteredCourseList);
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
        bundle.putString(BUNDLE_COURSE_CODE, filteredCourseList.get(position).getCode());

        NavHostFragment.findNavController(this).navigate(R.id.action_studentCourseListFragment_to_studentCourseDetailsFragment, bundle);
    }

    private void markCompleteOrIncomplete(String courseCode) {
        if (completedModules.contains(courseCode)) {
            completedModules.remove(courseCode);
        } else {
            completedModules.add(courseCode);
        }

        saveCompletedModules();
        studentCourseAdapter.setCompletedModules(getCompletedModules(prefs));
    }

    private void saveCompletedModules() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_COMPLETED_MODULES, TextUtils.join(",", completedModules));
        editor.apply();
    }
}
