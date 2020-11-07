package com.wintec.degreemap.ui.manager.manage_courses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.Course;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteCourseAdapter extends ArrayAdapter<Course> {
    private List<Course> courseList;

    public AutoCompleteCourseAdapter(@NonNull Context context) {
        super(context, 0);
    }

    public void setCourses(List<Course> courses) {
        this.courseList = courses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_item_autocomplete, parent, false);
        }

        TextView courseCodeTextView = convertView.findViewById(R.id.courseCode);
        TextView courseLongNameTextView = convertView.findViewById(R.id.courseLongName);

        Course courseItem = getItem(position);

        if (courseItem != null) {
            courseCodeTextView.setText(courseItem.getCode());
            courseLongNameTextView.setText(courseItem.getLongName());
        }
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return courseFilter;
    }

    private Filter courseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Course> suggestions = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(courseList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Course item : courseList) {
                    if (item.getCode().toLowerCase().contains(filterPattern)
                            || item.getLongName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Course) resultValue).getCode();
        }
    };
}