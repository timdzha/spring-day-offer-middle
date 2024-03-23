package com.onedayoffer.taskdistribution.exception;

import java.text.MessageFormat;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer employeeId) {
        super(MessageFormat.format("employee not found by id: {0}", employeeId));
    }
}
