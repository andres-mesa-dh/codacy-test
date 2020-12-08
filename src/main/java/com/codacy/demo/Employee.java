package com.codacy.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false)
    private Integer age;

    public Employee() {
    }

    public Employee(
        final String name,
        final String lastName,
        final Date birthDate
    ) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = Period.between(
            birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()
        ).getYears();
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

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!name.equals(employee.name)) return false;
        if (!lastName.equals(employee.lastName)) return false;
        if (!birthDate.equals(employee.birthDate)) return false;
        return age.equals(employee.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }
}
