package com.harshit.employeeportal.handlers;

import com.harshit.employeeportal.beans.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        CustomError err = new CustomError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Constraint Violation" ,details);

        return new ResponseEntity<>(err, err.getStatus());
    }
}
