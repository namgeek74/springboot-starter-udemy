package com.udemy.springbootdemo.dao;

import com.udemy.springbootdemo.entity.Course;
import com.udemy.springbootdemo.entity.Instructor;
import com.udemy.springbootdemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor instructor = entityManager.find(Instructor.class, theId);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the bi-direction
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );
        query.setParameter("data", theId);

        // execute the query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i "
                        + "JOIN FETCH i.courses "
                        + "WHERE i.id = :data", Instructor.class
        );
        query.setParameter("data", theId);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        Course course = entityManager.find(Course.class, theId);

        return course;
    }

    @Override
    @Transactional
    public void deleteInstructorByIdWithoutCourse(int theId) {
        // Instructor instructor = entityManager.find(Instructor.class, theId);
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i "
                        + "JOIN FETCH i.courses "
                        + "WHERE i.id = :data", Instructor.class
        );
        query.setParameter("data", theId);
        Instructor instructor = query.getSingleResult();
        List<Course> courses = instructor.getCourses();

        for (Course course :
                courses) {
            course.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourse(int theId) {
        Course course = entityManager.find(Course.class, theId);
        entityManager.remove(course);
    }
}
