package com.sicpatest.sicpaback.presentation.presenter;

import com.sicpatest.sicpaback.entity.Department;
import com.sicpatest.sicpaback.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEmployeePresenter {
    private UUID id;
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private Boolean status;
    private DepartmentPresenter departmentPresenter;
    private EmployeePresenter employeePresenter;
}
