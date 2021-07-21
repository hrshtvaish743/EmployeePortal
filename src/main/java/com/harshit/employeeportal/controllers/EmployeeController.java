package com.harshit.employeeportal.controllers;

import com.harshit.employeeportal.beans.Employee;
import com.harshit.employeeportal.beans.ResponseBean;
import com.harshit.employeeportal.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseBean> addEmployee(
            @RequestBody Employee employee
    ) {
        if (employeeService.addEmployee(employee)) {
            return ResponseEntity.of(Optional.of(new ResponseBean(HttpStatus.CREATED, null)));
        } else {
            return ResponseEntity.of(Optional.of(new ResponseBean(HttpStatus.BAD_REQUEST, null)));
        }
    }
}
