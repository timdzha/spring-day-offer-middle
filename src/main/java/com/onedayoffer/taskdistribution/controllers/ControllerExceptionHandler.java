package com.onedayoffer.taskdistribution.controllers;

import com.onedayoffer.taskdistribution.exception.EmployeeNotFoundException;
import com.onedayoffer.taskdistribution.exception.SortDirectionNotValidException;
import com.onedayoffer.taskdistribution.exception.TaskAlreadyInStatusException;
import com.onedayoffer.taskdistribution.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler {

    @ExceptionHandler({
            SortDirectionNotValidException.class,
            TaskAlreadyInStatusException.class,
            TaskAlreadyInStatusException.class
    })
    public ResponseEntity<ProblemDetail> handleSortDirectionNotValidBindException(SortDirectionNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("error", exception.getMessage());

        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problemDetail);
    }

    @ExceptionHandler({EmployeeNotFoundException.class, TaskNotFoundException.class})
    public ResponseEntity<ProblemDetail> handleSortDirectionNotValidBindException(RuntimeException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setProperty("error", exception.getMessage());

        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problemDetail);
    }
}
