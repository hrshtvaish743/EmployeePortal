package com.harshit.employeeportal.repositories;


import com.harshit.employeeportal.beans.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
