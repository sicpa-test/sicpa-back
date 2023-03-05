package com.sicpatest.sicpaback.service.impl;

import com.sicpatest.sicpaback.entity.Employee;
import com.sicpatest.sicpaback.entity.Enterprise;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentEmployeePresenter;
import com.sicpatest.sicpaback.presentation.presenter.EmployeePresenter;
import com.sicpatest.sicpaback.presentation.presenter.EnterprisePresenter;
import com.sicpatest.sicpaback.presentation.presenter.Paginator;
import com.sicpatest.sicpaback.repository.EmployeeRepository;
import com.sicpatest.sicpaback.service.DepartmentEmployeeService;
import com.sicpatest.sicpaback.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentEmployeeService deportmentEmployeeService;

    @Override
    public List<EmployeePresenter> getAllEmployees() {
        return employeeRepository.findAll().stream().map(this::toEmployeePresenter).collect(Collectors.toList());
    }

    @Override
    public EmployeePresenter getEmployeeById(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        Set<DepartmentEmployeePresenter> departmentEmployeePresenters = employee.getDepartmentEmployees().stream()
                .map(departmentEmployee -> deportmentEmployeeService.toDepartmentEmployeePresenter(departmentEmployee))
                .collect(Collectors.toSet());
        EmployeePresenter employeePresenter = toEmployeePresenter(employee);
        employeePresenter.setDepartmentEmployeePresenters(departmentEmployeePresenters);
        return employeePresenter;
    }

    @Override
    public EmployeePresenter toEmployeePresenter(Employee employee) {
        return EmployeePresenter.builder()
                .id(employee.getId())
                .createdBy(employee.getCreatedBy())
                .createdDate(employee.getCreatedDate())
                .modifiedBy(employee.getModifiedBy())
                .modifiedDate(employee.getModifiedDate())
                .status(employee.getStatus())
                .age(employee.getAge())
                .email(employee.getEmail())
                .name(employee.getName())
                .position(employee.getPosition())
                .surname(employee.getSurname())
                .build();
    }

    @Override
    @Transactional
    public EmployeePresenter saveUpdateEmployee(EmployeePresenter employeePresenter) {
        Employee employee = new Employee();
        if (employeePresenter.getId() != null) {
            employee = employeeRepository.findById(employeePresenter.getId()).get();
        }
        Employee finalEmployee = employee;

        finalEmployee.setCreatedBy(employeePresenter.getCreatedBy());
        finalEmployee.setCreatedDate(employeePresenter.getCreatedDate());
        finalEmployee.setModifiedBy(employeePresenter.getModifiedBy());
        finalEmployee.setModifiedDate(employeePresenter.getModifiedDate());
        finalEmployee.setStatus(employeePresenter.getStatus());
        finalEmployee.setName(employeePresenter.getName());
        finalEmployee.setSurname(employeePresenter.getSurname());
        finalEmployee.setPosition(employeePresenter.getPosition());
        finalEmployee.setAge(employeePresenter.getAge());
        finalEmployee.setEmail(employeePresenter.getEmail());

        Employee employeeSaved = employeeRepository.save(finalEmployee);
        return toEmployeePresenter(employeeSaved);
    }

    @Override
    public Paginator getEmployeesPaginated(String searchValue, Pageable pageable) {
        Set<EmployeePresenter> employeePresenters = new HashSet<>();
        Page<Employee> employeePage = employeeRepository.findByFilters(searchValue, pageable);
        employeePage.getContent().forEach(employee -> {
            EmployeePresenter employeePresenter = toEmployeePresenter(employee);
            employeePresenters.add(employeePresenter);
        });
        Set<EmployeePresenter> treeSet = new TreeSet(Comparator.comparing(EmployeePresenter::getId));
        treeSet.addAll(employeePresenters);
        Paginator paginator = new Paginator(employeePage.getTotalPages(), employeePage.getTotalElements(), treeSet);

        return paginator;
    }

}
