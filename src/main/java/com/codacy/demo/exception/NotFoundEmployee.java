package com.codacy.demo.exception;

public class NotFoundEmployee extends RuntimeException {
    public NotFoundEmployee(final Long id) {
        super(String.format("Not found employee with id: %d", id));
    }
}
