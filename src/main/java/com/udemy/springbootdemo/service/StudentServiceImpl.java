package com.udemy.springbootdemo.service;

import com.udemy.springbootdemo.entity.Student;
import com.udemy.springbootdemo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getListStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findStudentByName(String email) {
        log.info("This is an informational log message.");
        log.error("An error occurred.");
        return studentRepository.findStudentByName(email);
    }
}
