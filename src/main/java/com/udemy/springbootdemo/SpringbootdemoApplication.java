package com.udemy.springbootdemo;

import com.udemy.springbootdemo.dao.AppDAO;
import com.udemy.springbootdemo.dao.StudentDAO;
import com.udemy.springbootdemo.entity.Course;
import com.udemy.springbootdemo.entity.Instructor;
import com.udemy.springbootdemo.entity.InstructorDetail;
import com.udemy.springbootdemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
//        Student student = new Student("John 2", "Legend 2", "johnlegend2@gmail.com");
//
//        System.out.println("Saving the student");
//        studentDAO.save(student);
//
//        System.out.println("Saved student, generated id: " + student.getId());
//
//        System.out.println("Read student");
//        System.out.println("Student: " + studentDAO.findById(student.getId()));
//    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            createInstructorWithCourses(appDAO);
        };
    }

    private void createInstructor(AppDAO appDAO) {
        // create the instructor
//        Instructor tempInstructor = new Instructor("Chad", "Darby", "chaddarby@gmail.com");
//        InstructorDetail tempInstructorDetail = new InstructorDetail("youtube channel 1", "coding");
//        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhupatel@gmail.com");
        InstructorDetail tempInstructorDetail = new InstructorDetail("youtube channel 2", "coding 2");
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        System.out.println("Saving");
        appDAO.save(tempInstructor);
        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 1;
        Instructor instructor = appDAO.findInstructorById(theId);
        System.out.println(instructor);
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Start delete an instructor with it: " + theId);
        appDAO.deleteInstructorById(theId);
        System.out.println("Deleted!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 2;
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);
        System.out.println(instructorDetail);
        System.out.println(instructorDetail.getInstructor());
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Start delete an instructor detail with it: " + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Deleted successfully!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhupatel@gmail.com");
        InstructorDetail tempInstructorDetail = new InstructorDetail("madhu youtube channel", "coding");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Course course1 = new Course("math");
        Course course2 = new Course("physical");
        Course course3 = new Course("run");
        tempInstructor.add(course1);
        tempInstructor.add(course2);
        tempInstructor.add(course3);

        // save the instructor
        System.out.println("Saving");
        appDAO.save(tempInstructor);
        System.out.println("Done!");
    }

}