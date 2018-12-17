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

    public LoginUsecase(UserService userService,
                        UserToUserResumeDtoConverter userToUserResumeDtoConverter) {
        this.userService = userService;
        this.userToUserResumeDtoConverter = userToUserResumeDtoConverter;
    }

    @Transactional
    public Optional<UserResumeDto> login(LoginDto dto) {

        Optional<User> user = userService.findOneByEmail(dto.getEmail());

        if(!user.isPresent()){
            throw new UserNotFoundException("Usuário e/ou senha inválidos");
        }

        if(userService.isPasswordInvalid(dto.getPassword(), user.get().getPassword())){
            throw new UnauthorizedException();
        }

        User userBeforeLogin = userService.login(user.get());
        return Optional.ofNullable(userToUserResumeDtoConverter.convert(userBeforeLogin));
    }
}
