package com.andrewn.java2305spring;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Can't find employee with id: " + id);
    }
}
