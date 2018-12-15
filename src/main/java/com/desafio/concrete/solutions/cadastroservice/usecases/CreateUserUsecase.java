package com.desafio.concrete.solutions.cadastroservice.usecases;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserDtoToUserConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserResumeDtoConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import com.desafio.concrete.solutions.cadastroservice.usecases.service.PhoneService;
import com.desafio.concrete.solutions.cadastroservice.usecases.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CreateUserUsecase {

    private final UserService userService;
    private final PhoneService phoneService;
    private final UserDtoToUserConverter userDtoToUserConverter;
    private final UserToUserResumeDtoConverter userToUserToCreateDtoConverter;

    public CreateUserUsecase(UserService userService, PhoneService phoneService,
                             UserDtoToUserConverter userDtoToUserConverter,
                             UserToUserResumeDtoConverter userToUserToCreateDtoConverter) {
        this.userService = userService;
        this.phoneService = phoneService;
        this.userDtoToUserConverter = userDtoToUserConverter;
        this.userToUserToCreateDtoConverter = userToUserToCreateDtoConverter;
    }

    @Transactional
    public Optional<UserResumeDto> create(UserDto dto) throws CloneNotSupportedException {

        Optional<User> user = Optional.ofNullable(userDtoToUserConverter.convert(dto));

        if(user.isPresent()){
            Optional<User> userSaved = userService.create(user.get());
            phoneService.create(userSaved);

            if(userSaved.isPresent()){
                return Optional.ofNullable(userToUserToCreateDtoConverter.convert(userSaved.get().clone()));
            }
        }
        return Optional.empty();
    }
}
