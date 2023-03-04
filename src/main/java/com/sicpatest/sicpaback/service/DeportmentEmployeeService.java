package com.sicpatest.sicpaback.service;

import com.sicpatest.sicpaback.entity.DepartmentEmployee;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentEmployeePresenter;

public interface DeportmentEmployeeService {
    DepartmentEmployeePresenter toDepartmentEmployeePresenter(DepartmentEmployee departmentEmployee);
}
