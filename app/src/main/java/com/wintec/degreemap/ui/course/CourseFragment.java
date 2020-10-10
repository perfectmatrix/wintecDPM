package com.wintec.degreemap.ui.course;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.CourseItem;

import java.util.ArrayList;

public class CourseFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        ArrayList<CourseItem> exampleCourseList = new ArrayList<>();
        exampleCourseList.add(new CourseItem("COMP501", "Information Technology Operations"));
        exampleCourseList.add(new CourseItem("COMP615", "Data Centre Infrastructure"));
        exampleCourseList.add(new CourseItem("MATH602", "Mathematics for Programming"));
        exampleCourseList.add(new CourseItem("COMP717", "Advanced Web Technologies"));
        exampleCourseList.add(new CourseItem("INFO501", "Professional Practice"));
        exampleCourseList.add(new CourseItem("INFO601", "Database Modelling and SQL"));
        exampleCourseList.add(new CourseItem("COMP602", "Web Development"));
        exampleCourseList.add(new CourseItem("INFO603", "Systems Administration"));
        exampleCourseList.add(new CourseItem("COMP615", "Data Centre Infrastructure"));
        exampleCourseList.add(new CourseItem("BIZM701", "Business Essentials for IT Professionals"));
        exampleCourseList.add(new CourseItem("COMP713", "Web Application Project"));
        exampleCourseList.add(new CourseItem("DFNZ701", "Design Factory 1"));

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
        String pathway = getArguments().getString("pathway");

        // TODO: add constants for pathway
        switch (pathway) {
            case "network":
                pathwayTextView.setText("Network Engineering");
                pathwayTextView.setBackgroundColor(getResources().getColor(R.color.purple, getContext().getTheme()));
                break;
            case "web":
                pathwayTextView.setText("Web Development");
                pathwayTextView.setBackgroundColor(getResources().getColor(R.color.blue, getContext().getTheme()));
                break;
            case "database":
                pathwayTextView.setText("Database Architecture");
                pathwayTextView.setBackgroundColor(getResources().getColor(R.color.green, getContext().getTheme()));
                break;
            case "software":
                pathwayTextView.setText("Software Engineering");
                pathwayTextView.setBackgroundColor(getResources().getColor(R.color.red, getContext().getTheme()));
                break;
        }

    }
}
