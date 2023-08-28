package com.udemy.springbootdemo.dao;

import com.udemy.springbootdemo.entity.Student;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);
}
