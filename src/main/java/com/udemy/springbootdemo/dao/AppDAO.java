package com.udemy.springbootdemo.dao;

import com.udemy.springbootdemo.entity.Instructor;
import com.udemy.springbootdemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
