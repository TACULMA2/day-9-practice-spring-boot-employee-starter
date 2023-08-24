package com.afs.restapi.service;

import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.EmployeeNotFoundException;
import com.afs.restapi.repository.EmployeeJpaRepository;
import com.afs.restapi.repository.InMemoryEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeJpaRepository employeeJpaRepository;
    private final InMemoryEmployeeRepository inMemoryEmployeeRepository;

    public EmployeeService(EmployeeJpaRepository employeeJpaRepository, InMemoryEmployeeRepository inMemoryEmployeeRepository) {
        this.employeeJpaRepository = employeeJpaRepository;
        this.inMemoryEmployeeRepository = inMemoryEmployeeRepository;
    }

    public InMemoryEmployeeRepository getEmployeeRepository() {
        return inMemoryEmployeeRepository;
    }

    public List<Employee> findAll() {
        return employeeJpaRepository.findAll();
    }

    public Employee findById(Long id) {
        return getEmployeeRepository().findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public void update(Long id, Employee employee) {
        Employee toBeUpdatedEmployee = findById(id);
        if (employee.getSalary() != null) {
            toBeUpdatedEmployee.setSalary(employee.getSalary());
        }
        if (employee.getAge() != null) {
            toBeUpdatedEmployee.setAge(employee.getAge());
        }
    }

    public List<Employee> findAllByGender(String gender) {
        return getEmployeeRepository().findAllByGender(gender);
    }

    public Employee create(Employee employee) {
        return getEmployeeRepository().insert(employee);
    }

    public List<Employee> findByPage(Integer pageNumber, Integer pageSize) {
        return getEmployeeRepository().findByPage(pageNumber, pageSize);
    }

    public void delete(Long id) {
        inMemoryEmployeeRepository.deleteById(id);
    }
}
