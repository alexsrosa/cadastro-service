package br.com.system.solutions.cadastroservice.usecases;

import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.converters.UserDtoToUserConverter;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserResumeDtoConverter;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import br.com.system.solutions.cadastroservice.infrastructure.services.UserService;
import br.com.system.solutions.cadastroservice.infrastructure.services.PhoneService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;

/**
 * Classe que contêm a implementação do usecase de criação de um novo usuário.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
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
