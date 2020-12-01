package com.codacy.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class EmployeeDto {
    @JsonProperty
    private String name;

    @JsonProperty
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer age;

    public EmployeeDto(
        final String name,
        final String lastName,
        final Date birthDate,
        final Integer age
    ) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = age;
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
}
