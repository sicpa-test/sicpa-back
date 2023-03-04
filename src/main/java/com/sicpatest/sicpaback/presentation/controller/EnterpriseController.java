package com.sicpatest.sicpaback.presentation.controller;

import com.sicpatest.sicpaback.presentation.presenter.EnterprisePresenter;
import com.sicpatest.sicpaback.presentation.presenter.Paginator;
import com.sicpatest.sicpaback.service.EnterpriseService;
import com.sun.istack.NotNull;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Generated
@RestController
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/getAllEnterprises")
    public List<EnterprisePresenter> getAllEnterprises() {
        return enterpriseService.getAllEnterprises();
    }

    @GetMapping("/getEnterpriseById")
    public EnterprisePresenter getEnterpriseById(@RequestParam("enterpriseId") UUID enterpriseId) {
        return enterpriseService.getEnterpriseById(enterpriseId);
    }

    @PostMapping("saveUpdateEnterprise")
    public EnterprisePresenter saveUpdateEnterprise(@RequestBody EnterprisePresenter enterprisePresenter) {
        return enterpriseService.saveUpdateEnterprise(enterprisePresenter);
    }

    @GetMapping("/getEnterprisesPaginated")
    public Paginator getEnterprisesPaginated(String searchValue, Integer page, Integer size) {
        searchValue = searchValue.replace(' ', '%');
        Pageable pageable = PageRequest.of(page, size);

        return enterpriseService.getEnterprisesPaginated(searchValue, pageable);
    }
}
