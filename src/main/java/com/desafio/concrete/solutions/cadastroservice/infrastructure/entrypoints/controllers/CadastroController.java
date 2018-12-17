package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.controllers;

import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UserNotFoundException;
import com.desafio.concrete.solutions.cadastroservice.usecases.CreateUserUsecase;
import com.desafio.concrete.solutions.cadastroservice.usecases.GetUserProfileUsecase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/cadastro")
public class CadastroController {

    private final CreateUserUsecase createUserUseCase;
    private final GetUserProfileUsecase findOneUserUsecase;

    public CadastroController(CreateUserUsecase createUserUseCase,
                              GetUserProfileUsecase findOneUserUsecase) {
        this.createUserUseCase = createUserUseCase;
        this.findOneUserUsecase = findOneUserUsecase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResumeDto> create(@Valid @RequestBody UserDto dto) throws CloneNotSupportedException {
        return ResponseEntity.ok(createUserUseCase.create(dto).get());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResumeDto> getUserProfile(
            @RequestHeader(value="token", required = false) String token,
            @PathVariable UUID userId) throws CloneNotSupportedException {
        return findOneUserUsecase.findOne(userId, token)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException("Usuário não localizado."));
    }
}