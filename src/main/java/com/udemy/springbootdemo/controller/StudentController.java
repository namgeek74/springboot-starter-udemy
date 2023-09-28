package com.udemy.springbootdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.udemy.springbootdemo.entity.Student;
import com.udemy.springbootdemo.entity.StudentOnly;
import com.udemy.springbootdemo.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<StudentOnly> getListStudent() {
        return studentService.getListStudent();
    }

    @GetMapping("/redis")
    public List<StudentOnly> getDriverByRedis() throws JsonProcessingException {
        return studentService.getDriverByRedis();
    }
}
