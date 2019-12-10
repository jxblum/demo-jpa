package com.example.demojpa.repo;

import com.example.demojpa.model.Employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
