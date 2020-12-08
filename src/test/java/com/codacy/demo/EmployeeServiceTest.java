package com.codacy.demo;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = EmployeeService.class)
class EmployeeServiceTest {
    @MockBean
    private EmployeeRepository repository;

    @Autowired
    private EmployeeService service;

    @Test
    void shouldCreateEmployeeWhenDtoIsValid() {
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        doReturn(null).when(repository).save(employeeArgumentCaptor.capture());

        final Date birthDate = convertToDate("1993-07-23");
        final int age = Period.between(
            birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()
        ).getYears();
        final EmployeeDto dto = new EmployeeDto(
            "Name", "LastName", birthDate
        );

        service.createEmployee(dto);

        final Employee employee = employeeArgumentCaptor.getValue();
        assertEquals(dto.getName(), employee.getName());
        assertEquals(dto.getLastName(), employee.getLastName());
        assertEquals(dto.getBirthDate(), employee.getBirthDate());
        assertEquals(age, employee.getAge());
    }

    @Test
    void shouldReturnAnEmptyListWhenNoSavedEmployees() {
        doReturn(List.of()).when(repository).findAll();

        List<EmployeeDto> employeeDtoList = service.getAllEmployees();

        assertTrue(employeeDtoList.isEmpty());
    }

    @Test
    void shouldReturnListEmployeeDtoWhenSavedEmployees() {
        final String name = "Name 1";
        final String lastName = "LastName 1";
        final Date birthDate = convertToDate("1993-07-23");

        final String anotherName = "Name 2";
        final String anotherLastName = "LastName 2";
        final Date anotherBirthDate = convertToDate("1996-08-07");

        List<Employee> employeeList = List.of(
            new Employee(name, lastName, birthDate),
            new Employee(anotherName, anotherLastName, anotherBirthDate)
        );
        doReturn(employeeList).when(repository).findAll();

        final List<EmployeeDto> resultList = service.getAllEmployees();
        final List<EmployeeDto> expectedList = employeeList.stream()
            .map(EmployeeDto::new)
            .collect(Collectors.toList());
        assertIterableEquals(expectedList, resultList);
    }

    private Date convertToDate(final String dateAsString) {
        return Date.from(
            LocalDate.parse(dateAsString).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()
        );
    }
}
