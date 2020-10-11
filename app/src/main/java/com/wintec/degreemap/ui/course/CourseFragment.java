package com.wintec.degreemap.ui.course;

import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Module;
import com.wintec.degreemap.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

public class CourseFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        ArrayList<Module> exampleCourseList = new ArrayList<>();
        exampleCourseList.add(new Module("COMP501", "Information Technology Operations"));
        exampleCourseList.add(new Module("COMP615", "Data Centre Infrastructure"));
        exampleCourseList.add(new Module("MATH602", "Mathematics for Programming"));
        exampleCourseList.add(new Module("COMP717", "Advanced Web Technologies"));
        exampleCourseList.add(new Module("INFO501", "Professional Practice"));
        exampleCourseList.add(new Module("INFO601", "Database Modelling and SQL"));
        exampleCourseList.add(new Module("COMP602", "Web Development"));
        exampleCourseList.add(new Module("INFO603", "Systems Administration"));
        exampleCourseList.add(new Module("COMP615", "Data Centre Infrastructure"));
        exampleCourseList.add(new Module("BIZM701", "Business Essentials for IT Professionals"));
        exampleCourseList.add(new Module("COMP713", "Web Application Project"));
        exampleCourseList.add(new Module("DFNZ701", "Design Factory 1"));

        mRecyclerView = view.findViewById(R.id.recyclerview_all_courses);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mAdapter = new CourseAdapter(exampleCourseList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        // set pathway title and background color
        setPathwayTextViewFormatting(view);

        return view;
    }

    private void setPathwayTextViewFormatting(View view) {
        TextView pathwayTextView = view.findViewById(R.id.pathwayTextView);
        int pathway = getArguments().getInt(DashboardFragment.BUNDLE_PATHWAY);

        switch (pathway) {
            case Module.PATHWAY_NETWORK_ENGINEERING:
                pathwayTextView.setText("Network Engineering");
                pathwayTextView.setBackgroundColor(getResources().getColor(R.color.purple, getContext().getTheme()));
                break;
            case Module.PATHWAY_WEB_DEVELOPMENT:
                pathwayTextView.setText("Web Development");
                pathwayTextView.setBackgroundColor(getResources().getColor(R.color.blue, getContext().getTheme()));
                break;
            case Module.PATHWAY_DATABASE_ARCHITECTURE:
                pathwayTextView.setText("Database Architecture");
                pathwayTextView.setBackgroundColor(getResources().getColor(R.color.green, getContext().getTheme()));
                break;
            case Module.PATHWAY_SOFTWARE_ENGINEERING:
                pathwayTextView.setText("Software Engineering");
                pathwayTextView.setBackgroundColor(getResources().getColor(R.color.red, getContext().getTheme()));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
