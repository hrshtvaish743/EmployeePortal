package com.harshit.employeeportal.services;

import com.harshit.employeeportal.beans.Employee;
import com.harshit.employeeportal.repositories.EmployeeRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class EmployeeServiceTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("Harshit",
                "Vaish",
                "Male",
                "22-10-1995",
                "Product Development"
        );
    }

    @Test
    @DisplayName("Should add Employee to the database")
    void addEmployeeTest() {
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertThat(employeeService.addEmployee(employee)).isEqualTo(true);
    }

    @Test
    @DisplayName("Should not add Employee to the database")
    void addEmployeeTestForValidation() {
        when(employeeRepository.save(any(Employee.class))).thenThrow(ConstraintViolationException.class);
        assertThrows(ConstraintViolationException.class, () -> {
            employeeService.addEmployee(employee);
        });
    }

    @Test
    @DisplayName("Should return sorted list of employees")
    void getAllEmployeesTest() {
        when(employeeRepository.findAll()).thenReturn(Stream.of(
                new Employee(null, "Bob", "Builder", "Male", "22-10-1995", "Product Development"),
                new Employee(null, "Alan", "Turing", "Male", "22-10-1995", "Product Development"),
                new Employee(null, "Isaac", "Newton", "Male", "22-10-1995", "Product Development"),
                new Employee(null, "Donald", "Knuth", "Male", "22-10-1995", "Product Development")
        ).collect(Collectors.toList()));
        Optional<List<Employee>> allEmployees = employeeService.getAllEmployees();
        allEmployees.ifPresent(employees -> assertThat(isSorted(employees))
                .isEqualTo(true));
    }

    boolean isSorted(List<Employee> employees) {
        if (employees.isEmpty() || employees.size() == 1) {
            return true;
        }

        Iterator<Employee> iter = employees.iterator();
        Employee current, previous = iter.next();
        Comparator<Employee> employeeComparator = Comparator.comparing(Employee::getFirstName);
        while (iter.hasNext()) {
            current = iter.next();
            if (employeeComparator.compare(previous, current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}