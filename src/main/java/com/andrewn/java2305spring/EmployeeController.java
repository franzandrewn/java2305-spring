package com.andrewn.java2305spring;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee one(@PathVariable("id") Long id) {
        // найти сотрудника по id и если такого нет, вернуть ничего (null)
//        return employeeRepository.findById(id).orElse(null);
        // найти сотрудника по id и если такого нет, вернуть объект с пустыми полями
//        return employeeRepository.findById(id).orElse(new Employee());
        // найти сотрудника по id и если такого нет, бросить исключение
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
    }

    @PostMapping("/employees")
    public Employee add(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @PutMapping("/employees/{id}")
    public Employee change(@PathVariable("id") Long id, @RequestBody Employee changedEmployee) {
        // Изменить сотрудника, если он найден по id, иначе - бросить исключение EmployeeNotFoundException
//        return employeeRepository.findById(id)
//                .map(foundEmployee -> {
//                    foundEmployee.setName(changedEmployee.getName());
//                    foundEmployee.setRole(changedEmployee.getRole());
//                    return employeeRepository.save(foundEmployee);
//                }).orElseThrow(() -> new EmployeeNotFoundException(id));
        // Изменить сотрудника, если он найден по id, иначе - создает нового сотрудника
        return employeeRepository.findById(id)
                .map(foundEmployee -> {
                    foundEmployee.setName(changedEmployee.getName());
                    foundEmployee.setRole(changedEmployee.getRole());
                    return employeeRepository.save(foundEmployee);
                }).orElseGet(() -> employeeRepository.save(changedEmployee));
    }

    @GetMapping("/employees/by-role/{role}")
    public List<Employee> filterByRole(@PathVariable("role") String role) {
        return employeeRepository.findByRoleOrderByNameDesc(role);
    }
}
