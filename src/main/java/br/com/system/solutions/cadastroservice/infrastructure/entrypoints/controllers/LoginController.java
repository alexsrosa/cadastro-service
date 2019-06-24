package br.com.system.solutions.cadastroservice.infrastructure.entrypoints.controllers;

import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.LoginDto;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UserNotFoundException;
import br.com.system.solutions.cadastroservice.usecases.LoginUsecase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Classe com implementações do Endpoint de login.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginUsecase loginUsecase;

    public LoginController(LoginUsecase loginUsecase) {
        this.loginUsecase = loginUsecase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResumeDto> login(@Valid @RequestBody LoginDto dto) throws CloneNotSupportedException {
        return loginUsecase.login(dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException("Usuário não localizado."));
    }
}