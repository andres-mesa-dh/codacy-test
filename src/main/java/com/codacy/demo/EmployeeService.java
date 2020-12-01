package com.codacy.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(final EmployeeDto employeeDto) {
        employeeRepository.save(new Employee(
            employeeDto.getName(), employeeDto.getLastName(), employeeDto.getBirthDate()
        ));
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
            .stream()
            .map(it -> new EmployeeDto(
                it.getName(), it.getLastName(), it.getBirthDate(), it.getAge()
            ))
            .collect(Collectors.toList());
    }
}
