package com.udemy.springbootdemo.service;

import com.udemy.springbootdemo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public Optional<Employee> getEmployeeById(int id);

    public Employee saveEmployee(Employee employee);

    public void deleteEmployeeById(int id);
}
