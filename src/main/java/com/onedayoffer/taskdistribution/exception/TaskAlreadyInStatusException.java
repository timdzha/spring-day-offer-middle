package com.onedayoffer.taskdistribution.exception;

public class TaskAlreadyInStatusException extends RuntimeException {
    public TaskAlreadyInStatusException(String message) {
        super(message);
    }
}
