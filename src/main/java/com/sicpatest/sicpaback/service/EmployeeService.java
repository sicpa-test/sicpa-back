package com.sicpatest.sicpaback.service;

import com.sicpatest.sicpaback.entity.Employee;
import com.sicpatest.sicpaback.presentation.presenter.EmployeePresenter;
import com.sicpatest.sicpaback.presentation.presenter.Paginator;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeePresenter> getAllEmployees();
    EmployeePresenter getEmployeeById(UUID employeeId);
    EmployeePresenter toEmployeePresenter(Employee employee);
    EmployeePresenter saveUpdateEmployee(EmployeePresenter employeePresenter);
    Paginator getEmployeesPaginated(String searchValue, Pageable pageable);
}
