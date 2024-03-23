package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.DTO.TaskDTO;
import com.onedayoffer.taskdistribution.DTO.TaskStatus;
import com.onedayoffer.taskdistribution.exception.TaskAlreadyInStatusException;
import com.onedayoffer.taskdistribution.exception.TaskNotFoundException;
import com.onedayoffer.taskdistribution.repositories.TaskRepository;
import com.onedayoffer.taskdistribution.repositories.entities.Employee;
import com.onedayoffer.taskdistribution.repositories.entities.Task;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final Type TASK_DTO_LIST_TYPE = new TypeToken<List<TaskDTO>>() {}.getType();

    private final EmployeeService employeeService;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public List<TaskDTO> getTasksByEmployeeId(Integer employeeId) {
        final EmployeeDTO employee = employeeService.getOneEmployee(employeeId);
        return modelMapper.map(employee.getTasks(), TASK_DTO_LIST_TYPE);
    }

    @Transactional
    public void changeTaskStatus(Integer employeeId, Integer taskId, TaskStatus status) {
        Employee oneEmployee = employeeService.getEmployee(employeeId);
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isEmpty()) {
            throw new TaskNotFoundException(taskId);
        }
        Task task = taskOpt.get();
        if (task.getStatus() == status) {
            throw new TaskAlreadyInStatusException(format("task: {0} already in status: {1}", taskId, status));
        }
        task.setStatus(status);
        task.setEmployee(oneEmployee);
        taskRepository.save(task);
    }

    @Transactional
    public void postNewTask(Integer employeeId, TaskDTO newTask) {
        final Employee employee = employeeService.getEmployee(employeeId);
        Task task = modelMapper.map(newTask, Task.class);
        task.setEmployee(employee);
        taskRepository.save(task);
    }
}
