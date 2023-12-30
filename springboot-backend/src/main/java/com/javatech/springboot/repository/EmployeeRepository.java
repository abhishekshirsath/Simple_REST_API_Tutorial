package com.javatech.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatech.springboot.model.Employee;

//@Repository : no need to add internally provide this annotations (3)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
