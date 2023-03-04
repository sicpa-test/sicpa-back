package com.sicpatest.sicpaback.service.impl;

import com.sicpatest.sicpaback.entity.Department;
import com.sicpatest.sicpaback.entity.Enterprise;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentEmployeePresenter;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentPresenter;
import com.sicpatest.sicpaback.presentation.presenter.EnterprisePresenter;
import com.sicpatest.sicpaback.repository.DepartmentRepository;
import com.sicpatest.sicpaback.repository.EnterpriseRepository;
import com.sicpatest.sicpaback.service.DepartmentService;
import com.sicpatest.sicpaback.service.DeportmentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DeportmentEmployeeService deportmentEmployeeService;
    @Autowired
    private EnterpriseRepository enterpriseRepository;


    @Override
    public List<DepartmentPresenter> getAllDepartments() {
        return departmentRepository.findAll().stream().map(this::toDepartmentPresenter).collect(Collectors.toList());
    }

    @Override
    public DepartmentPresenter getDepartmentById(UUID departmentId) {
        Department department = departmentRepository.findById(departmentId).get();
        Set<DepartmentEmployeePresenter> departmentEmployeePresenters = department.getDepartmentEmployees().stream()
                .map(departmentEmployee -> deportmentEmployeeService.toDepartmentEmployeePresenter(departmentEmployee))
                .collect(Collectors.toSet());
        DepartmentPresenter departmentPresenter = toDepartmentPresenter(department);
        departmentPresenter.setDepartmentEmployeePresenters(departmentEmployeePresenters);
        return departmentPresenter;
    }

    @Override
    public DepartmentPresenter toDepartmentPresenter(Department department) {
        return DepartmentPresenter.builder()
                .id(department.getId())
                .createdBy(department.getCreatedBy())
                .createdDate(department.getCreatedDate())
                .modifiedBy(department.getModifiedBy())
                .modifiedDate(department.getModifiedDate())
                .status(department.getStatus())
                .description(department.getDescription())
                .name(department.getName())
                .phone(department.getPhone())
                .enterprisePresenter(EnterprisePresenter.builder()
                        .id(department.getEnterprise().getId())
                        .address(department.getEnterprise().getAddress())
                        .name(department.getEnterprise().getName())
                        .phone(department.getEnterprise().getPhone())
                        .build())
                .build();
    }

    @Override
    @Transactional
    public DepartmentPresenter saveUpdateDepartment(DepartmentPresenter departmentPresenter) {
        Department department = new Department();
        if (departmentPresenter.getId() != null) {
            department = departmentRepository.findById(departmentPresenter.getId()).get();
        }
        Enterprise enterprise = enterpriseRepository.findById(departmentPresenter.getEnterprisePresenter().getId()).get();
        Department finalDepartment = department;
        finalDepartment.setCreatedBy(departmentPresenter.getCreatedBy());
        finalDepartment.setCreatedDate(departmentPresenter.getCreatedDate());
        finalDepartment.setModifiedBy(departmentPresenter.getModifiedBy());
        finalDepartment.setModifiedDate(departmentPresenter.getModifiedDate());
        finalDepartment.setStatus(departmentPresenter.getStatus());
        finalDepartment.setDescription(departmentPresenter.getDescription());
        finalDepartment.setName(departmentPresenter.getName());
        finalDepartment.setPhone(departmentPresenter.getPhone());
        finalDepartment.setEnterprise(enterprise);
        Department enterpriseSaved = departmentRepository.save(finalDepartment);
        return toDepartmentPresenter(enterpriseSaved);
    }
}
