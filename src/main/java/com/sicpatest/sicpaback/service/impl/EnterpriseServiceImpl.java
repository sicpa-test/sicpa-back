package com.sicpatest.sicpaback.service.impl;

import com.sicpatest.sicpaback.entity.Department;
import com.sicpatest.sicpaback.entity.Enterprise;
import com.sicpatest.sicpaback.exception.ValidationException;
import com.sicpatest.sicpaback.presentation.presenter.DepartmentPresenter;
import com.sicpatest.sicpaback.presentation.presenter.EnterprisePresenter;
import com.sicpatest.sicpaback.presentation.presenter.Paginator;
import com.sicpatest.sicpaback.repository.EnterpriseRepository;
import com.sicpatest.sicpaback.service.DepartmentService;
import com.sicpatest.sicpaback.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public List<EnterprisePresenter> getAllEnterprises() {
        return enterpriseRepository.findAll().stream().map(this::toEnterprisePresenter).collect(Collectors.toList());
    }

    @Override
    public EnterprisePresenter getEnterpriseById(UUID enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
        Set<DepartmentPresenter> departmentPresenters = enterprise.getDepartments().stream()
                .map(department -> departmentService.toDepartmentPresenter(department))
                .collect(Collectors.toSet());
        EnterprisePresenter enterprisePresenter = toEnterprisePresenter(enterprise);
        enterprisePresenter.setDepartmentPresenters(departmentPresenters);
        return enterprisePresenter;
    }

    @Override
    public EnterprisePresenter toEnterprisePresenter(Enterprise enterprise) {
        return EnterprisePresenter.builder()
                .id(enterprise.getId())
                .createdBy(enterprise.getCreatedBy())
                .createdDate(enterprise.getCreatedDate())
                .modifiedBy(enterprise.getModifiedBy())
                .modifiedDate(enterprise.getModifiedDate())
                .status(enterprise.getStatus())
                .address(enterprise.getAddress())
                .name(enterprise.getName())
                .phone(enterprise.getPhone())
                .build();
    }

    @Override
    @Transactional
    public EnterprisePresenter saveUpdateEnterprise(EnterprisePresenter enterprisePresenter) {

        Enterprise enterprise = new Enterprise();
        if (enterprisePresenter.getId() != null) {
            enterprise = enterpriseRepository.findById(enterprisePresenter.getId()).get();
            Enterprise finalEnterprise1 = enterprise;
            Set<Department> departments=enterprise.getDepartments().stream().filter(department ->
                    department.getEnterprise().getId().equals(finalEnterprise1.getId())).collect(Collectors.toSet());
            if(!enterprisePresenter.getStatus()&&!departments.isEmpty()){
                throw new ValidationException("No se puede inactivar porque existen departamentos asociados");
            }
        }
        Enterprise finalEnterprise = enterprise;

        finalEnterprise.setCreatedBy(enterprisePresenter.getCreatedBy());
        finalEnterprise.setCreatedDate(enterprisePresenter.getCreatedDate());
        finalEnterprise.setModifiedBy(enterprisePresenter.getModifiedBy());
        finalEnterprise.setModifiedDate(enterprisePresenter.getModifiedDate());
        finalEnterprise.setStatus(enterprisePresenter.getStatus());
        finalEnterprise.setAddress(enterprisePresenter.getAddress());
        finalEnterprise.setName(enterprisePresenter.getName());
        finalEnterprise.setPhone(enterprisePresenter.getPhone());

        Enterprise enterpriseSaved = enterpriseRepository.save(finalEnterprise);
        return toEnterprisePresenter(enterpriseSaved);

    }

    @Override
    public Paginator getEnterprisesPaginated(String searchValue, Pageable pageable) {
        Set<EnterprisePresenter> enterprisePresenters = new HashSet<>();
        Page<Enterprise> enterprisePage = enterpriseRepository.findByFilters(searchValue, pageable);
        enterprisePage.getContent().forEach(enterprise -> {
            EnterprisePresenter enterprisePresenter = toEnterprisePresenter(enterprise);
            enterprisePresenters.add(enterprisePresenter);
        });
        Set<EnterprisePresenter> treeSet = new TreeSet(Comparator.comparing(EnterprisePresenter::getId));
        treeSet.addAll(enterprisePresenters);
        Paginator paginator = new Paginator(enterprisePage.getTotalPages(), enterprisePage.getTotalElements(), treeSet);

        return paginator;
    }




}
