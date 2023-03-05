package com.sicpatest.sicpaback.presentation.controller;


import com.sicpatest.sicpaback.presentation.presenter.EmployeePresenter;
import com.sicpatest.sicpaback.presentation.presenter.Paginator;
import com.sicpatest.sicpaback.service.EmployeeService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Generated
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployees")
    public List<EmployeePresenter> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployeeById")
    public EmployeePresenter getEnterpriseById(@RequestParam("employeeId") UUID employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    @PostMapping("saveUpdateEmployee")
    public EmployeePresenter saveUpdateEmployee(@RequestBody EmployeePresenter employeePresenter) {
        return employeeService.saveUpdateEmployee(employeePresenter);
    }
    @GetMapping("/getEmployeesPaginated")
    public Paginator getEmployeesPaginated(String searchValue, Integer page, Integer size) {
        searchValue = searchValue.replace(' ', '%');
        Pageable pageable = PageRequest.of(page, size);

        return employeeService.getEmployeesPaginated(searchValue, pageable);
    }
}
