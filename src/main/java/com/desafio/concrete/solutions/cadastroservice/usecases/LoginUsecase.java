package com.desafio.concrete.solutions.cadastroservice.usecases;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserResumeDtoConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.LoginDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UnauthorizedException;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UserNotFoundException;
import com.desafio.concrete.solutions.cadastroservice.usecases.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LoginUsecase {

    private final UserService userService;
    private final UserToUserResumeDtoConverter userToUserResumeDtoConverter;

    public LoginUsecase(UserService userService, UserToUserResumeDtoConverter userToUserResumeDtoConverter) {
        this.userService = userService;
        this.userToUserResumeDtoConverter = userToUserResumeDtoConverter;
    }

    @Transactional
    public Optional<UserResumeDto> login(LoginDto dto) {

        Optional<User> oneByEmail = userService.findOneByEmail(dto.getEmail());

        if(!oneByEmail.isPresent()){
            throw new UserNotFoundException("Usuário e/ou senha inválidos");
        }

        Optional<User> user = userService.findOneByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if(!user.isPresent()){
            throw new UnauthorizedException();
        }

        return Optional.ofNullable(userToUserResumeDtoConverter.convert(user.get()));
    }
}
