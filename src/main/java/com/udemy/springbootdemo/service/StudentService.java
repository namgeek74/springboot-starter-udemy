package com.udemy.springbootdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.udemy.springbootdemo.entity.StudentOnly;

import java.util.List;

public interface StudentService {
    List<StudentOnly> getListStudent();

    List<StudentOnly> getDriverByRedis() throws JsonProcessingException;

}
