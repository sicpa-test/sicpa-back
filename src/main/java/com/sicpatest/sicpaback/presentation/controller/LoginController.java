package com.sicpatest.sicpaback.presentation.controller;

import com.sicpatest.sicpaback.exception.ValidationException;
import com.sicpatest.sicpaback.presentation.presenter.UserPresenter;
import lombok.Generated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Generated
@RestController
public class LoginController {

    @GetMapping("/login")
    public UserPresenter login(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        if (!username.equals("romario.aponte")) {
            throw new ValidationException("Usuario o contraseña incorrecta");
        }
        if (!password.equals("romario.aponte")) {
            throw new ValidationException("Usuario o contraseña incorrecta");
        }
        return UserPresenter.builder()
                .id(UUID.fromString("f1c01a2d-161f-479d-9684-0bd55556413e"))
                .name("William Romario Aponte Zurita")
                .build();
    }
}
