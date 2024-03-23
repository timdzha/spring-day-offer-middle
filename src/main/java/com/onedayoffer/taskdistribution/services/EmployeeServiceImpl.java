package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.exception.EmployeeNotFoundException;
import com.onedayoffer.taskdistribution.exception.SortDirectionNotValidException;
import com.onedayoffer.taskdistribution.repositories.EmployeeRepository;
import com.onedayoffer.taskdistribution.repositories.entities.Employee;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Type EMPLOYEE_DTO_LIST_TYPE = new TypeToken<List<EmployeeDTO>>() {}.getType();

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDTO> getEmployees(@Nullable String sortDirection) {
        if (StringUtils.isBlank(sortDirection)) {
            return modelMapper.map(employeeRepository.findAll(), EMPLOYEE_DTO_LIST_TYPE);
        }
        Optional<Sort.Direction> direction = Sort.Direction.fromOptionalString(sortDirection);
        if (direction.isEmpty()) {
            throw new SortDirectionNotValidException(sortDirection);
        }
        return modelMapper.map(
                employeeRepository.findAllAndSort(
                        Sort.by(direction.get(), "fio")), EMPLOYEE_DTO_LIST_TYPE);
    }

    @Transactional
    public EmployeeDTO getOneEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException(id);
        }
        return modelMapper.map(employee.get(), EmployeeDTO.class);
    }

    @Override
    public Employee getEmployee(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException(id);
        }
        return employee.get();
    }
}
