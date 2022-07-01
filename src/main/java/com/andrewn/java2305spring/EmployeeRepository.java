package com.andrewn.java2305spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRoleOrderByNameDesc(String role);

    @Query("select e from Employee e where e.name = ?1")
    List<Employee> findByName(String name);
}
