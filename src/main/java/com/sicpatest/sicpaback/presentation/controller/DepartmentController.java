package com.sicpatest.sicpaback.presentation.controller;

import com.sicpatest.sicpaback.presentation.presenter.DepartmentPresenter;
import com.sicpatest.sicpaback.presentation.presenter.Paginator;
import com.sicpatest.sicpaback.service.DepartmentService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Generated
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getAllDepartments")
    public List<DepartmentPresenter> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/getDepartmentById")
    public DepartmentPresenter getDepartmentById(@RequestParam("departmentId") UUID departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }
    @PostMapping("saveUpdateDepartment")
    public DepartmentPresenter saveUpdateDepartment(@RequestBody DepartmentPresenter departmentPresenter) {
        return departmentService.saveUpdateDepartment(departmentPresenter);
    }
    @GetMapping("/getDepartmentsPaginated")
    public Paginator getDepartmentsPaginated(String searchValue, Integer page, Integer size) {
        searchValue = searchValue.replace(' ', '%');
        Pageable pageable = PageRequest.of(page, size);

        return departmentService.getDepartmentsPaginated(searchValue, pageable);
    }
}
