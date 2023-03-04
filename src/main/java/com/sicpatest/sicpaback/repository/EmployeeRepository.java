package com.sicpatest.sicpaback.repository;

import com.sicpatest.sicpaback.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    List<Employee> findAll();

}
