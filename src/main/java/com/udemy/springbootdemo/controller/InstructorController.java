package com.udemy.springbootdemo.controller;

import com.udemy.springbootdemo.dto.CourseDTO;
import com.udemy.springbootdemo.dto.InstructorDTO;
import com.udemy.springbootdemo.dao.AppDAO;
import com.udemy.springbootdemo.dto.CourseDTO;
import com.udemy.springbootdemo.dto.InstructorDTO;
import com.udemy.springbootdemo.entity.Course;
import com.udemy.springbootdemo.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private AppDAO appDAO;

    @Autowired
    public InstructorController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @GetMapping("/{id}")
    public InstructorDTO getInstructorById(@PathVariable("id") int id) {
        Instructor instructor = appDAO.findInstructorById(id);
        InstructorDTO instructorDTO = new InstructorDTO(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getEmail()
        );

        List<Course> courses = instructor.getCourses();
        List<CourseDTO> coursesDTO = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getId(), course.getTitle());
            coursesDTO.add(courseDTO);
        }
        instructorDTO.setCourses(coursesDTO);

        return instructorDTO;
    }
}
