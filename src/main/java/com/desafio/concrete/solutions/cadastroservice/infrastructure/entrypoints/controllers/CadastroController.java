package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.controllers;

import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UserNotFoundException;
import com.desafio.concrete.solutions.cadastroservice.usecases.CreateUserUsecase;
import com.desafio.concrete.solutions.cadastroservice.usecases.FindOneUserUsecase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/cadastro")
public class CadastroController {

    private final CreateUserUsecase createUserUseCase;
    private final FindOneUserUsecase findOneUserUsecase;

    public CadastroController(CreateUserUsecase createUserUseCase,
                              FindOneUserUsecase findOneUserUsecase) {
        this.createUserUseCase = createUserUseCase;
        this.findOneUserUsecase = findOneUserUsecase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResumeDto> create(@Valid @RequestBody UserDto dto) throws CloneNotSupportedException {
        return ResponseEntity.ok(createUserUseCase.create(dto).get());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findOne(@PathVariable UUID userId) throws CloneNotSupportedException {
        return findOneUserUsecase.findOne(userId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException("Usuário não localizado."));
    }
}