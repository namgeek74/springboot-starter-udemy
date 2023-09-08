package com.udemy.springbootdemo;

import com.udemy.springbootdemo.dao.AppDAO;
import com.udemy.springbootdemo.dao.StudentDAO;
import com.udemy.springbootdemo.entity.Course;
import com.udemy.springbootdemo.entity.Instructor;
import com.udemy.springbootdemo.entity.InstructorDetail;
import com.udemy.springbootdemo.entity.Review;
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
            deleteCourseAndReviews(appDAO);
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

    private void findInstructorWithoutCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding the instructor id: " + theId);
        Instructor instructor = appDAO.findInstructorById(theId);
        System.out.println("instructor: " + instructor);
        System.out.println("Done");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);
        Instructor instructor = appDAO.findInstructorById(theId);
        System.out.println("instructor: " + instructor);
        System.out.println("Finding courses for instructor id: " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        instructor.setCourses(courses);
        System.out.println("courses: " + instructor.getCourses());
        System.out.println("Done");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);
        System.out.println("instructor: " + instructor);
        System.out.println("courses: " + instructor.getCourses());
        System.out.println("Done");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        Instructor instructor = appDAO.findInstructorById(theId);
        instructor.setFirstName("New first name");

        appDAO.updateInstructor(instructor);
        System.out.println("Update successfully");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 10;
        Course course = appDAO.findCourseById(theId);
        course.setTitle("New title updated");
        System.out.println("Updating course id: " + theId);

        appDAO.updateCourse(course);
        System.out.println("Update course successfully");
    }

    private void deleteInstructorByIdWithoutCourse(AppDAO appDAO) {
        int theId = 1;
        appDAO.deleteInstructorByIdWithoutCourse(theId);
        System.out.println("Deleted successfully!");
    }

    private void deleteCourse(AppDAO appDAO) {
        int theId = 10;
        appDAO.deleteCourse(theId);
        System.out.println("Deleted successfully!");

    }

    private void createCourseAndReview(AppDAO appDAO) {
        Course course = new Course("Spring Boot starter");
        course.addReview(new Review("Great, i love it"));
        course.addReview(new Review("Cool course, i'm done"));
        course.addReview(new Review("What a dumb course, you are an idiot!"));

        appDAO.save(course);
        System.out.println("Created Successfully");
    }

    private void findCourseAndReviewByCourseId(AppDAO appDAO) {
        int theId = 10;
        Course course = appDAO.findCourseAndReviewByCourseId(theId);
        System.out.println("course: " + course);
        System.out.println("reviews: " + course.getReviews());
        System.out.println("Done");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;
        appDAO.deleteCourse(theId);
        System.out.println("Done");
    }

}