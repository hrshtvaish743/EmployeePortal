package com.harshit.employeeportal.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class ResponseBean {

    private HttpStatus status;

    private Object data;
}
