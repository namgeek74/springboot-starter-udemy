package com.udemy.springbootdemo.controller;

import com.udemy.springbootdemo.entity.Student;
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
    public List<Student> getListStudent() {
        return studentService.getListStudent();
    }

    @GetMapping("/{email}")
    public List<Student> getListStudentByName(@PathVariable String email) {
        return studentService.findStudentByName(email);
    }
}
