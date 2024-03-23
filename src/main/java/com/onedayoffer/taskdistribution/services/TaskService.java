package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.DTO.TaskDTO;
import com.onedayoffer.taskdistribution.DTO.TaskStatus;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getTasksByEmployeeId(Integer id);

    void changeTaskStatus(Integer employeeId, Integer taskId, TaskStatus status);

    void postNewTask(Integer employeeId, TaskDTO newTask);
}
