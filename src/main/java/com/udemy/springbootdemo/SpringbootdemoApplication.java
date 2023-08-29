package com.udemy.springbootdemo;

import com.udemy.springbootdemo.dao.StudentDAO;
import com.udemy.springbootdemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
//        return runner -> {
//            createStudent(studentDAO);
//        };
//    }
//
//    private void createStudent(StudentDAO studentDAO) {
//        System.out.println("Creating new student object");
//        Student student = new Student("John", "Legend", "johnlegend@gmail.com");
//
//        System.out.println("Saving the student");
//        studentDAO.save(student);
//
//        System.out.println("Saved student, generated id: " + student.getId());
//
//        System.out.println("Read student");
//        System.out.println("Student: " + studentDAO.findById(student.getId()));
//    }

}