package com.udemy.springbootdemo;

import com.udemy.springbootdemo.dao.AccountDAO;
import com.udemy.springbootdemo.dao.AppDAO;
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
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private void createInstructor(AppDAO appDAO) {
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

    private void deleteCourse(AppDAO appDAO) {
        int theId = 10;
        appDAO.deleteCourse(theId);
        System.out.println("Done");
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course course = new Course("spring boot starter");
        course.addStudent(new Student("Nam", "Nguyen", "namnguyen@gmail.com"));
        course.addStudent(new Student("Huy", "Nguyen", "huynguyen@gmail.com"));
        course.addStudent(new Student("Khanh", "Tran", "khanhtran@gmail.com"));
        System.out.println("Starting");
        appDAO.save(course);
        System.out.println("Done");
        System.out.println("Done");

    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int theId = 10;
        Course course = appDAO.findCourseAndStudentsByCourseId(theId);
        System.out.println("course: " + course);
        System.out.println("students: " + course.getStudents());
        System.out.println("Done");
    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int id = 1;
        Student student = appDAO.findStudentAndCoursesByStudentId(id);
        System.out.println("student: " + student);
        System.out.println("courses: " + student.getCourses());
        System.out.println("Done");
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int id = 1;
        Student student = appDAO.findStudentAndCoursesByStudentId(id);
        student.addCourse(new Course("bitcoin and more"));
        student.addCourse(new Course("youtuber"));
        System.out.println("Starting");
        appDAO.update(student);
        System.out.println("Done");
    }

    private void deleteStudent(AppDAO appDAO) {
        int id = 1;
        appDAO.deleteStudent(id);
        System.out.println("Done");
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO) {
        // call the business method
        accountDAO.addAccount();

        StringBuilder result = accountDAO.testAfterReturning(1);
        System.out.println("result: " + result);
    }

    private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
        // call the business method
        try {
            accountDAO.throwDemo();
            System.out.println("success in main");
        } catch (Exception e) {
            System.out.println("exception in main: " + e);
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {
        return runner -> {
            String str = "Nam Nguyen";
            int hashValue = str.hashCode();
            System.out.println("hashed value of '" + str + "' is: " + hashValue);
        };
    }
}