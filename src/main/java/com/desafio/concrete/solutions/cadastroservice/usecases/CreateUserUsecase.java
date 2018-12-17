package com.desafio.concrete.solutions.cadastroservice.usecases;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserDtoToUserConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserResumeDtoConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.services.UserService;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.services.PhoneService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;

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

        Optional<UserEntity> user = Optional.ofNullable(userDtoToUserConverter.convert(dto));

        if (user.isPresent()) {
            Optional<UserEntity> userSaved = userService.create(user.get());
            phoneService.create(userSaved);

            if (userSaved.isPresent()) {
                return Optional.ofNullable(userToUserToCreateDtoConverter.convert(userSaved.get().clone()));
            }
        }
        return Optional.empty();
    }
}
