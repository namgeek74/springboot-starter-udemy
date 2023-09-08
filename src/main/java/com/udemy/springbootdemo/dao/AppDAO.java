package com.udemy.springbootdemo.dao;

import com.udemy.springbootdemo.entity.Course;
import com.udemy.springbootdemo.entity.Instructor;
import com.udemy.springbootdemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void updateInstructor(Instructor theInstructor);

    void updateCourse(Course theCourse);

    Course findCourseById(int theId);

    void deleteInstructorByIdWithoutCourse(int theId);

    void deleteCourse(int theId);
}
