package com.sicpatest.sicpaback.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments_employees")
public class DepartmentEmployee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private Boolean status;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee employee;

}
