package com.sicpatest.sicpaback.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sicpatest.sicpaback.entity.Department;
import com.sicpatest.sicpaback.entity.DepartmentEmployee;
import com.sicpatest.sicpaback.entity.Employee;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentEmployeePresenter;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentPresenter;
import com.sicpatest.sicpaback.presentation.presenter.EmployeePresenter;
import com.sicpatest.sicpaback.repository.DepartmentEmployeeRepository;
import com.sicpatest.sicpaback.repository.DepartmentRepository;
import com.sicpatest.sicpaback.repository.EmployeeRepository;
import com.sicpatest.sicpaback.service.DepartmentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {
    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<DepartmentEmployeePresenter> getAllDepartmentEmployees() {
        return departmentEmployeeRepository.findAll().stream().map(this::toDepartmentEmployeePresenter).collect(Collectors.toList());
    }

    @Override
    public DepartmentEmployeePresenter getDepartmentEmployeeById(UUID departmentEmployeeId) {
        return toDepartmentEmployeePresenter(departmentEmployeeRepository.findById(departmentEmployeeId).get());
    }

    @Override
    @Transactional
    public DepartmentEmployeePresenter saveUpdateDepartmentEmployee(DepartmentEmployeePresenter departmentEmployeePresenter) {
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        if (departmentEmployeePresenter.getId() != null) {
            departmentEmployee = departmentEmployeeRepository.findById(departmentEmployeePresenter.getId()).get();
        }
        Department department = departmentRepository.findById(departmentEmployeePresenter.getDepartmentPresenter().getId()).get();
        Employee employee = employeeRepository.findById(departmentEmployeePresenter.getEmployeePresenter().getId()).get();
        DepartmentEmployee finalDepartmentEmployee = departmentEmployee;
        finalDepartmentEmployee.setCreatedBy(departmentEmployeePresenter.getCreatedBy());
        finalDepartmentEmployee.setCreatedDate(departmentEmployeePresenter.getCreatedDate());
        finalDepartmentEmployee.setModifiedBy(departmentEmployeePresenter.getModifiedBy());
        finalDepartmentEmployee.setModifiedDate(departmentEmployeePresenter.getModifiedDate());
        finalDepartmentEmployee.setStatus(departmentEmployeePresenter.getStatus());
        finalDepartmentEmployee.setDepartment(department);
        finalDepartmentEmployee.setEmployee(employee);
        DepartmentEmployee departmentEmployeeSaved = departmentEmployeeRepository.save(finalDepartmentEmployee);
        return toDepartmentEmployeePresenter(departmentEmployeeSaved);
    }

    @Override
    public DepartmentEmployeePresenter toDepartmentEmployeePresenter(DepartmentEmployee departmentEmployee) {
        return DepartmentEmployeePresenter.builder()
                .id(departmentEmployee.getId())
                .createdBy(departmentEmployee.getCreatedBy())
                .createdDate(departmentEmployee.getCreatedDate())
                .modifiedBy(departmentEmployee.getModifiedBy())
                .modifiedDate(departmentEmployee.getModifiedDate())
                .status(departmentEmployee.getStatus())
                .departmentPresenter(DepartmentPresenter.builder()
                        .id(departmentEmployee.getDepartment().getId())
                        .createdBy(departmentEmployee.getDepartment().getCreatedBy())
                        .createdDate(departmentEmployee.getDepartment().getCreatedDate())
                        .modifiedBy(departmentEmployee.getDepartment().getModifiedBy())
                        .modifiedDate(departmentEmployee.getDepartment().getModifiedDate())
                        .status(departmentEmployee.getDepartment().getStatus())
                        .description(departmentEmployee.getDepartment().getDescription())
                        .name(departmentEmployee.getDepartment().getName())
                        .phone(departmentEmployee.getDepartment().getPhone())
                        .build())
                .employeePresenter(EmployeePresenter.builder()
                        .id(departmentEmployee.getEmployee().getId())
                        .createdBy(departmentEmployee.getEmployee().getCreatedBy())
                        .createdDate(departmentEmployee.getEmployee().getCreatedDate())
                        .modifiedBy(departmentEmployee.getEmployee().getModifiedBy())
                        .modifiedDate(departmentEmployee.getEmployee().getModifiedDate())
                        .status(departmentEmployee.getEmployee().getStatus())
                        .name(departmentEmployee.getEmployee().getName())
                        .surname(departmentEmployee.getEmployee().getSurname())
                        .build())
                .build();
    }

}
