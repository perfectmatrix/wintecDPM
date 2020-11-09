package com.wintec.degreemap.data.repository;

import com.google.firebase.database.DatabaseReference;
import com.wintec.degreemap.data.firebaselivedata.CourseDetailsLiveData;
import com.wintec.degreemap.data.firebaselivedata.CourseListLiveData;
import com.wintec.degreemap.data.model.Course;
import com.wintec.degreemap.util.FirebaseUtils;

import java.util.ArrayList;

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
        if (courseList == null) {
            courseList = new CourseListLiveData(FirebaseUtils.getCourseRef().orderByChild("semester"));
            return courseList;
        }
        return courseList;
    }

    public CourseDetailsLiveData getCourseDetails(String courseCode) {
        return new CourseDetailsLiveData(FirebaseUtils.getCourseRef().child(courseCode));
    }

    public void saveCourse(String courseCode,
                           Course course,
                           ArrayList<String> pathway,
                           ArrayList<String> preRequisite,
                           ArrayList<String> coRequisite) {
        DatabaseReference courseRef = FirebaseUtils.getCourseRef();
        courseRef.child(courseCode).setValue(course);

        // Save pathway, pre-requisites and co-requisites
        for (String p : pathway) {
            courseRef.child(courseCode).child("pathway").child(p).setValue(true);
        }

        for (String moduleCode : preRequisite) {
            courseRef.child(courseCode).child("preRequisite").child(moduleCode).setValue(true);
        }

        for (String moduleCode : coRequisite) {
            courseRef.child(courseCode).child("coRequisite").child(moduleCode).setValue(true);
        }
    }

    public void deleteCourse(String courseCode) {
        DatabaseReference courseRef = FirebaseUtils.getCourseRef();
        courseRef.child(courseCode).removeValue();
    }
}
