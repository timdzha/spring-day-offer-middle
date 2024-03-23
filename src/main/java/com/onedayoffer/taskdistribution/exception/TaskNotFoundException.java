package com.onedayoffer.taskdistribution.exception;

import static java.text.MessageFormat.format;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Integer taskId) {
        super(format("task not found by id: {0}", taskId));
    }
}
