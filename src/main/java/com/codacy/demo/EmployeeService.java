package com.codacy.demo;

import com.codacy.demo.exception.NotFoundEmployee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void createEmployee(final EmployeeDto employeeDto) {
        repository.save(new Employee(
            employeeDto.getName(), employeeDto.getLastName(), employeeDto.getBirthDate()
        ));
    }

    public List<EmployeeDto> getAllEmployees() {
        return repository.findAll()
            .stream()
            .map(EmployeeDto::new)
            .collect(Collectors.toList());
    }

    public EmployeeDto deleteEmployee(final Long id) {
        return repository.findById(id)
            .map(EmployeeDto::new)
            .orElseThrow(() -> new NotFoundEmployee(id));
    }
}
