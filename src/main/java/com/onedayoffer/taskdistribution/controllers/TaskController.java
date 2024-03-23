package com.onedayoffer.taskdistribution.controllers;

import com.onedayoffer.taskdistribution.DTO.TaskDTO;
import com.onedayoffer.taskdistribution.DTO.TaskStatus;
import com.onedayoffer.taskdistribution.exception.TaskStatusNotValidException;
import com.onedayoffer.taskdistribution.services.TaskService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "employees/{id}/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getTasksByEmployeeId(@PathVariable Integer id) {
        return taskService.getTasksByEmployeeId(id);
    }

    @PatchMapping("/{taskId}/status")
    @ResponseStatus(HttpStatus.OK)
    public void changeTaskStatus(
            @PathVariable(value = "id") Integer employeeId,
            @PathVariable(value = "taskId") Integer taskId,
            @RequestParam(value = "newStatus") String newStatus
    ) {

        if (taskStatusIsValid(newStatus)) {
            throw new TaskStatusNotValidException(newStatus);
        }
        taskService.changeTaskStatus(employeeId, taskId, TaskStatus.valueOf(newStatus.toUpperCase()));
    }

    private boolean taskStatusIsValid(String newStatus) {
        return StringUtils.isBlank(newStatus)
                && Arrays.stream(TaskStatus.values())
                    .noneMatch(taskStatus -> taskStatus.name().equals(newStatus.toUpperCase()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postNewTask(
            @PathVariable(value = "id") Integer employeeId,
            @RequestBody TaskDTO taskDTO
    ) {
        taskService.postNewTask(employeeId, taskDTO);
    }
}