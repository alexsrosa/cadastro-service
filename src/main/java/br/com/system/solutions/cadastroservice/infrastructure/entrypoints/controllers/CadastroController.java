package br.com.system.solutions.cadastroservice.infrastructure.entrypoints.controllers;

import br.com.system.solutions.cadastroservice.usecases.CreateUserUsecase;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.exceptions.GeneralException;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UserNotFoundException;
import br.com.system.solutions.cadastroservice.usecases.GetUserProfileUsecase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;
import javax.validation.Valid;

/**
 * Classe com implementações do Endpoint de cadastro de novos usuários.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@RestController
@RequestMapping(value = "/api/cadastro")
public class CadastroController {

    private final CreateUserUsecase createUserUseCase;
    private final GetUserProfileUsecase getUserProfileUsecase;

    public CadastroController(CreateUserUsecase createUserUseCase,
                              GetUserProfileUsecase findOneUserUsecase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserProfileUsecase = findOneUserUsecase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResumeDto> create(@Valid @RequestBody UserDto dto, UriComponentsBuilder uri) {

        return createUserUseCase.create(dto)
                .map((UserResumeDto userResDto) ->
                        ResponseEntity.created(uri.path("/cadastro/{userId}")
                                .buildAndExpand(userResDto.getId()).toUri())
                                .body(userResDto))
                .orElseThrow(() -> new GeneralException("Erro ao efetuar o cadastro do usuário."));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResumeDto> getUserProfile(
            @RequestHeader(value = "token", required = false) String token,
            @PathVariable UUID userId) throws CloneNotSupportedException {
        return getUserProfileUsecase.findOne(userId, token)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException("Usuário não localizado."));
    }
}