package com.sicpatest.sicpaback.presentation.controller;

import com.sicpatest.sicpaback.presentation.presenter.DepartmentEmployeePresenter;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentPresenter;
import com.sicpatest.sicpaback.service.DepartmentEmployeeService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Generated
@RestController
public class DepartmentEmployeeController {
    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @GetMapping("/getAllDepartmentEmployees")
    public List<DepartmentEmployeePresenter> getAllDepartmentEmployees() {
        return departmentEmployeeService.getAllDepartmentEmployees();
    }

    @GetMapping("/getDepartmentEmployeeById")
    public DepartmentEmployeePresenter getDepartmentEmployeeById(@RequestParam("departmentEmployeeId") UUID departmentEmployeeId) {
        return departmentEmployeeService.getDepartmentEmployeeById(departmentEmployeeId);
    }
    @PostMapping("saveUpdateDepartmentEmployee")
    public DepartmentEmployeePresenter saveUpdateDepartment(@RequestBody DepartmentEmployeePresenter departmentEmployeePresenter) {
        return departmentEmployeeService.saveUpdateDepartmentEmployee(departmentEmployeePresenter);
    }
}
