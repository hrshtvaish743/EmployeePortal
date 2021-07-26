package com.harshit.employeeportal.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshit.employeeportal.beans.Employee;
import com.harshit.employeeportal.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    EmployeeController employeeController;


    @MockBean
    EmployeeService employeeService;

    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();
    }

    @Test
    @DisplayName("Should add employee to the database")
    void addEmployeeTest() throws Exception {
        Employee employee = new Employee("Harshit",
                "Vaish",
                "Male",
                "22-10-1995",
                "Product Development"
        );
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(Boolean.TRUE);
        MvcResult mvcResult = mockMvc.perform(
                post("/employee/add")
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode response = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        assertThat(response.get("status").asText()).isEqualTo(HttpStatus.CREATED.getReasonPhrase().toUpperCase(Locale.ROOT));
    }

    @Test
    @DisplayName("Should give Bad Request error")
    void addEmployeeTestForBadRequest() throws Exception {
        Employee employee = new Employee("Harshit",
                "Vaish",
                "Male",
                "22-10-1995",
                "Product Development"
        );
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(Boolean.FALSE);
        MvcResult mvcResult = mockMvc.perform(
                post("/employee/add")
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode response = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        assertThat(response.get("status").asText()).isEqualTo("BAD_REQUEST");
    }

    @Test
    @DisplayName("Should give Bad request in response")
    void addEmployeeTestForSerialization() throws Exception {
        String requestData = "{\"firstName\":\"\",\"lastName\":\"Vaish\",\"dateOfBirth\":\"22/10/1995\",\"department\":\"\"}";
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(Boolean.FALSE);
        MvcResult mvcResult = mockMvc.perform(
                post("/employee/add")
                        .content(requestData)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode response = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        assertThat(response.get("status").asText()).isEqualTo("BAD_REQUEST");
    }

    @Test
    @DisplayName("Should get list of all employees")
    void getAllEmployees() throws Exception {
        List<Employee> employeeList = Stream.of(
                new Employee("Bob", "Builder", "Male", "22-10-1995", "Product Development"),
                new Employee("Alan", "Turing", "Male", "22-10-1995", "Product Development"),
                new Employee("Isaac", "Newton", "Male", "22-10-1995", "Product Development"),
                new Employee("Donald", "Knuth", "Male", "22-10-1995", "Product Development")
        ).collect(Collectors.toList());

        when(employeeService.getAllEmployees()).thenReturn(Optional.of(employeeList));

        MvcResult mvcResult = mockMvc.perform(
                get("/employee/getall"))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode response = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        assertThat(response.isObject()).isTrue();
        assertThat(response.get("data").isArray()).isTrue();
        Boolean isInvalid = Stream.of(response.get("data")).anyMatch(jsonNode -> !objectMapper.canSerialize(Employee.class));
        assertThat(isInvalid).isFalse();
    }

}