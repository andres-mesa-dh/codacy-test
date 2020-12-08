package com.codacy.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class EmployeeDto {
    @JsonProperty(required = true)
    private final String name;

    @JsonProperty(required = true)
    private final String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty(required = true)
    private final Date birthDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer age;

    public EmployeeDto(final Employee employee) {
        this.name = employee.getName();
        this.lastName = employee.getLastName();
        this.birthDate = employee.getBirthDate();
        this.age = employee.getAge();
    }

    public EmployeeDto(
        final String name,
        final String lastName,
        final Date birthDate
    ) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeDto that = (EmployeeDto) o;

        if (!name.equals(that.name)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!birthDate.equals(that.birthDate)) return false;
        return Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}
