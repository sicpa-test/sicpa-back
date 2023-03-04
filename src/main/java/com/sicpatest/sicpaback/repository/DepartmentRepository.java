package com.sicpatest.sicpaback.repository;

import com.sicpatest.sicpaback.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, UUID> {
    List<Department> findAll();
}
