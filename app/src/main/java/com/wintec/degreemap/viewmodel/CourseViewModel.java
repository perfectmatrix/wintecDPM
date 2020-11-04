package com.wintec.degreemap.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wintec.degreemap.data.firebaselivedata.CourseDetailsLiveData;
import com.wintec.degreemap.data.firebaselivedata.CourseListLiveData;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.data.repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends ViewModel {
    private CourseRepository mCourseRepository;

    public CourseViewModel() {
        this.mCourseRepository = CourseRepository.getInstance();
    }

    public CourseListLiveData getCourseList() {
        return mCourseRepository.getCourseList();
    }

    public CourseDetailsLiveData getCourseDetails(String courseCode) {
        return mCourseRepository.getCourseDetails(courseCode);
    }
}