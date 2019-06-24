package br.com.system.solutions.cadastroservice.usecases;

import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserResumeDtoConverter;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.LoginDto;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UnauthorizedException;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UserNotFoundException;
import br.com.system.solutions.cadastroservice.infrastructure.helpers.CryptPasswordHelper;
import br.com.system.solutions.cadastroservice.infrastructure.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;

/**
 * Classe que contêm a implementação do usecase para efetuar um novo login no sistema.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
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
    public Optional<UserResumeDto> login(LoginDto dto) throws CloneNotSupportedException {

        Optional<UserEntity> user = userService.findOneByEmail(dto.getEmail());

        if(!user.isPresent()){
            throw new UserNotFoundException("Usuário e/ou senha inválidos");
        }

        if(CryptPasswordHelper.isPasswordInvalid(dto.getPassword(), user.get().getPassword())){
            throw new UnauthorizedException();
        }

        UserEntity userBeforeLogin = userService.login(user.get());
        return Optional.ofNullable(userToUserResumeDtoConverter.convert(userBeforeLogin.clone()));
    }
}
