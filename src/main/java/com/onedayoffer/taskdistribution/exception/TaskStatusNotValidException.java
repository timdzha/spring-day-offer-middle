package com.onedayoffer.taskdistribution.exception;

import com.onedayoffer.taskdistribution.DTO.TaskStatus;

import java.text.MessageFormat;
import java.util.Arrays;

public class TaskStatusNotValidException extends RuntimeException {
    public TaskStatusNotValidException(String newStatus) {
        super(MessageFormat.format("Task status not valid:{0}. Valid statuses: {1}",
                newStatus, Arrays.toString(TaskStatus.values())));
    }
}
