package com.wintec.degreemap.data.repository;

import com.wintec.degreemap.data.firebaselivedata.CourseDetailsLiveData;
import com.wintec.degreemap.data.firebaselivedata.CourseListLiveData;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.util.FirebaseUtils;

public class CourseRepository {
    private static CourseRepository sCourseRepository;
    private CourseListLiveData courseList;

    public static CourseRepository getInstance() {
        if (sCourseRepository == null) {
            sCourseRepository = new CourseRepository();
        }
        return sCourseRepository;
    }

    public CourseListLiveData getCourseList() {
        if(courseList == null)
        {
            courseList = new CourseListLiveData(FirebaseUtils.getCourseRef());
            return courseList;
        }
        return courseList;
    }

    public CourseDetailsLiveData getCourseDetails(String courseKey) {
        return new CourseDetailsLiveData(FirebaseUtils.getCourseRef().child(courseKey));
    }
}
