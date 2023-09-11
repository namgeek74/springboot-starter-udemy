package com.udemy.springbootdemo.service;

import com.udemy.springbootdemo.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getListStudent();

    List<Student> findStudentByName(String email);
}
