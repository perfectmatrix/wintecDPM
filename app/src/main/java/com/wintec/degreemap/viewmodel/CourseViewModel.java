package com.wintec.degreemap.viewmodel;

import androidx.lifecycle.ViewModel;

import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.data.repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends ViewModel {
    private CourseRepository courseRepository;
    public List<Course> courseList;

    public CourseViewModel() {
        this.courseRepository = new CourseRepository();
        courseList = courseRepository.getCourses();
    }

    public List<Course> getCourses() {
        return courseList;
    }
}