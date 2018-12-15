package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.controllers;

import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.LoginDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import com.desafio.concrete.solutions.cadastroservice.usecases.LoginUsecase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    private final LoginUsecase loginUsecase;

    public LoginController(LoginUsecase loginUsecase) {
        this.loginUsecase = loginUsecase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResumeDto> login(@Valid @RequestBody LoginDto dto) {
        return ResponseEntity.ok(loginUsecase.login(dto).get());
    }
}