package com.sicpatest.sicpaback.service;

import com.sicpatest.sicpaback.entity.DepartmentEmployee;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentEmployeePresenter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DepartmentEmployeeService {
    DepartmentEmployeePresenter toDepartmentEmployeePresenter(DepartmentEmployee departmentEmployee);
    List<DepartmentEmployeePresenter> getAllDepartmentEmployees();
    DepartmentEmployeePresenter getDepartmentEmployeeById(UUID departmentEmployeeId);
    DepartmentEmployeePresenter saveUpdateDepartmentEmployee(DepartmentEmployeePresenter departmentEmployeePresenter);
}
