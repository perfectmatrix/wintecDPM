package com.wintec.degreemap.viewmodel;

import androidx.lifecycle.ViewModel;

import com.wintec.degreemap.data.firebaselivedata.CourseDetailsLiveData;
import com.wintec.degreemap.data.firebaselivedata.CourseListLiveData;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.data.repository.CourseRepository;

import java.util.ArrayList;

public class CourseViewModel extends ViewModel {
    private CourseRepository courseRepository;

    public CourseViewModel() {
        this.courseRepository = CourseRepository.getInstance();
    }

    public CourseListLiveData getCourseList() {
        return courseRepository.getCourseList();
    }

    public CourseDetailsLiveData getCourseDetails(String courseCode) {
        return courseRepository.getCourseDetails(courseCode);
    }

    public void saveCourse(String courseCode,
                           Course course,
                           ArrayList<String> pathway,
                           ArrayList<String> coRequisite,
                           ArrayList<String> preRequisite) {
        courseRepository.saveCourse(courseCode, course, pathway, coRequisite, preRequisite);
    }
}