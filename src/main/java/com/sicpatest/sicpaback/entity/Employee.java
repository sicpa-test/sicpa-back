package com.sicpatest.sicpaback.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private Boolean status;
    private Integer age;
    private String email;
    private String name;
    private String position;
    private String surname;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<DepartmentEmployee> departmentEmployees = new HashSet<>();
}
