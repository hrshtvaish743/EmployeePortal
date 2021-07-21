package com.harshit.employeeportal.services;

import com.harshit.employeeportal.beans.Employee;
import com.harshit.employeeportal.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean addEmployee(Employee employee) {
        //TODO : Validate employee. Return false if invalid
        employeeRepository.save(employee);
        return true;
    }
}
