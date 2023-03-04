package com.sicpatest.sicpaback.presentation.presenter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnterprisePresenter {
    private UUID id;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    private String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;
    private Boolean status;
    private String address;
    private String name;
    private String phone;
    @Builder.Default
    private Set<DepartmentPresenter> departmentPresenters = new HashSet<>();
}
