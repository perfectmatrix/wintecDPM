package com.wintec.degreemap.data.repository;

import androidx.lifecycle.LiveData;

import com.wintec.degreemap.data.firebaselivedata.CourseListLiveData;
import com.wintec.degreemap.data.model.Course;

import java.util.List;

public class CourseRepository {
    private static CourseRepository sCourseRepository;
    private LiveData<List<Course>> courseList;

    public CourseRepository() {
        courseList = new CourseListLiveData();
    }

    public static CourseRepository getInstance() {
        if (sCourseRepository == null) {
            sCourseRepository = new CourseRepository();
        }
        return sCourseRepository;
    }

    public LiveData<List<Course>> getCourseList() {
        return courseList;
    }
}
