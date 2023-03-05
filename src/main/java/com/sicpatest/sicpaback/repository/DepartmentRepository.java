package com.sicpatest.sicpaback.repository;

import com.sicpatest.sicpaback.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, UUID> {
    List<Department> findAll();

    @Query("SELECT d " +
            "FROM  Department d " +
            "WHERE LOWER(name) like LOWER(CONCAT('%',:searchValue,'%')) " +
            "AND status is true")
    Page<Department> findByFilters(@Param("searchValue") String searchValue,
                                   @Param("pageable") Pageable pageable);}
