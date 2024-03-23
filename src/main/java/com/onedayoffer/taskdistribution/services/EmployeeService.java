package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.repositories.entities.Employee;
import org.springframework.lang.Nullable;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getEmployees(@Nullable String sortDirection);

    EmployeeDTO getOneEmployee(Integer id);

    Employee getEmployee(Integer id);
}
