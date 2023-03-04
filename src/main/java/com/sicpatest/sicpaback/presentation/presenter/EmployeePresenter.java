package com.sicpatest.sicpaback.presentation.presenter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sicpatest.sicpaback.entity.DepartmentEmployee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePresenter {
    private UUID id;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    private String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;
    private Boolean status;
    private Integer age;
    private String email;
    private String name;
    private String position;
    private String surname;
    private Set<DepartmentEmployeePresenter>departmentEmployeePresenters = new HashSet<>();
}
