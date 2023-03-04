package com.sicpatest.sicpaback.service.impl;

import com.sicpatest.sicpaback.entity.DepartmentEmployee;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentEmployeePresenter;
import com.sicpatest.sicpaback.service.DeportmentEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentEmployeeServiceImpl implements DeportmentEmployeeService {


    @Override
    public DepartmentEmployeePresenter toDepartmentEmployeePresenter(DepartmentEmployee departmentEmployee) {
        return DepartmentEmployeePresenter.builder()
                .id(departmentEmployee.getId())
                .createdBy(departmentEmployee.getCreatedBy())
                .createdDate(departmentEmployee.getCreatedDate())
                .modifiedBy(departmentEmployee.getModifiedBy())
                .modifiedDate(departmentEmployee.getModifiedDate())
                .status(departmentEmployee.getStatus())
                //.departmentPresenter(departmentService.toDepartmentPresenter(departmentEmployee.getDepartment()))
                //.employeePresenter(employeeService.toEmployeePresenter(departmentEmployee.getEmployee()))
                .build();
    }
}
