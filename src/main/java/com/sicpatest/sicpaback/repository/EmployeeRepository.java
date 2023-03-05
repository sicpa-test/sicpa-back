package com.sicpatest.sicpaback.repository;

import com.sicpatest.sicpaback.entity.Employee;
import com.sicpatest.sicpaback.entity.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    List<Employee> findAll();

    @Query("SELECT e " +
            "FROM  Employee e " +
            "WHERE LOWER(name) like LOWER(CONCAT('%',:searchValue,'%')) " +
            "AND status is true")
    Page<Employee> findByFilters(@Param("searchValue") String searchValue,
                                   @Param("pageable") Pageable pageable);}
