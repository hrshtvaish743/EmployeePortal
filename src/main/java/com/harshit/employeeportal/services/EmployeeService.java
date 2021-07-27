package com.harshit.employeeportal.services;

import com.harshit.employeeportal.beans.Employee;
import com.harshit.employeeportal.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    public Optional<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        employeeList.sort(Comparator.comparing(Employee::getFirstName));
        return Optional.of(employeeList);
    }
}
