package com.udemy.springbootdemo.repository;

import com.udemy.springbootdemo.entity.StudentOnly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentOnlyRepository extends JpaRepository<StudentOnly, Integer> {
}
