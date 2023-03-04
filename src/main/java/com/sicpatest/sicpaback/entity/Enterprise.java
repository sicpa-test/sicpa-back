package com.sicpatest.sicpaback.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Data
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enterprises")
public class Enterprise implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private Boolean status;
    private String address;
    private String name;
    private String phone;

    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Builder.Default
    private Collection<Department> departments = new HashSet<>();
}
