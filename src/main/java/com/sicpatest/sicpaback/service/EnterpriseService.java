package com.sicpatest.sicpaback.service;

import com.sicpatest.sicpaback.entity.Enterprise;
import com.sicpatest.sicpaback.presentation.presenter.EnterprisePresenter;
import com.sicpatest.sicpaback.presentation.presenter.Paginator;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EnterpriseService {

    List<EnterprisePresenter> getAllEnterprises();
    EnterprisePresenter getEnterpriseById(UUID enterpriseId);
    EnterprisePresenter toEnterprisePresenter(Enterprise enterprise);
    EnterprisePresenter saveUpdateEnterprise(EnterprisePresenter enterprisePresenter);
    Paginator getEnterprisesPaginated(String searchValue, Pageable pageable);
}
