package com.sicpatest.sicpaback.repository;

import com.sicpatest.sicpaback.entity.DepartmentEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentEmployeeRepository extends CrudRepository<DepartmentEmployee, UUID> {
    List<DepartmentEmployee> findAll();

}
