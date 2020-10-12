package com.wintec.degreemap.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.data.repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends ViewModel {
    private CourseRepository courseRepository;
    public LiveData<List<Course>> courseList;

    public CourseViewModel() {
        this.courseRepository = CourseRepository.getInstance();
    }

    public LiveData<List<Course>> getCourseList() {
        if (courseList == null) {
            courseList = courseRepository.getCourseList();
        }

        return courseList;
    }
}