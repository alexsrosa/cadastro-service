package com.desafio.concrete.solutions.cadastroservice.usecases;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserDtoToUserConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserDtoConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import com.desafio.concrete.solutions.cadastroservice.usecases.service.PhoneService;
import com.desafio.concrete.solutions.cadastroservice.usecases.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindOneUserUsecase {

    private final UserService userService;
    private final UserToUserDtoConverter userToUserDtoConverter;

    public FindOneUserUsecase(UserService userService,
                              UserToUserDtoConverter userToUserDtoConverter) {
        this.userService = userService;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    @Transactional
    public Optional<UserDto> findOne(UUID id) throws CloneNotSupportedException {
        Optional<User> one = userService.findOne(id);

        if(!one.isPresent()){
            return Optional.empty();
        }
        return Optional.ofNullable(userToUserDtoConverter.convert(one.get().clone()));
    }
}
