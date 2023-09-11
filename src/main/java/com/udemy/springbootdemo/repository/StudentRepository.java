package com.udemy.springbootdemo.repository;

import com.udemy.springbootdemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.emailName = :email")
    List<Student> findStudentByName(
            @Param("email") String email
    );
}
