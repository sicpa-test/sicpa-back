package com.sicpatest.sicpaback.presentation.presenter;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    private String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;
    private Boolean status;
    private DepartmentPresenter departmentPresenter;
    private EmployeePresenter employeePresenter;
}
