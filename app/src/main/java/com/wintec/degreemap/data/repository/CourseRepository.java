package com.wintec.degreemap.data.repository;

import com.wintec.degreemap.data.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    public List<Course> getCourses() {
        ArrayList<Course> exampleCourseList = new ArrayList<>();

        exampleCourseList.add(new Course("COMP501", "Information Technology Operations"));
        exampleCourseList.add(new Course("COMP615", "Data Centre Infrastructure"));
        exampleCourseList.add(new Course("MATH602", "Mathematics for Programming"));
        exampleCourseList.add(new Course("COMP717", "Advanced Web Technologies"));
        exampleCourseList.add(new Course("INFO501", "Professional Practice"));
        exampleCourseList.add(new Course("INFO601", "Database Modelling and SQL"));
        exampleCourseList.add(new Course("COMP602", "Web Development"));
        exampleCourseList.add(new Course("INFO603", "Systems Administration"));
        exampleCourseList.add(new Course("COMP615", "Data Centre Infrastructure"));
        exampleCourseList.add(new Course("BIZM701", "Business Essentials for IT Professionals"));
        exampleCourseList.add(new Course("COMP713", "Web Application Project"));
        exampleCourseList.add(new Course("DFNZ701", "Design Factory 1"));

        return exampleCourseList;
    }
}
