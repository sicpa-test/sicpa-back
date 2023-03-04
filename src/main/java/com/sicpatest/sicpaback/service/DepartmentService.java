package com.sicpatest.sicpaback.service;

import com.sicpatest.sicpaback.entity.Department;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentPresenter;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<DepartmentPresenter> getAllDepartments();
    DepartmentPresenter getDepartmentById(UUID departmentId);
    DepartmentPresenter toDepartmentPresenter(Department department);
    DepartmentPresenter saveUpdateDepartment(DepartmentPresenter departmentPresenter);
}
