package br.com.system.solutions.cadastroservice.usecases;

import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserResumeDtoConverter;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UnauthorizedException;
import br.com.system.solutions.cadastroservice.infrastructure.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;

/**
 * Classe que contêm a implementação do usecase para busca de um perfil existente.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Service
public class GetUserProfileUsecase {

    private final UserService userService;
    private final UserToUserResumeDtoConverter userToUserResumeDtoConverter;

    public GetUserProfileUsecase(UserService userService,
                                 UserToUserResumeDtoConverter userToUserResumeDtoConverter) {
        this.userService = userService;
        this.userToUserResumeDtoConverter = userToUserResumeDtoConverter;
    }

    @Transactional
    public Optional<UserResumeDto> findOne(UUID id, String token) throws CloneNotSupportedException {

        validateToken(token);

        Optional<UserEntity> user = userService.findOne(id);
        if(!user.isPresent()){
            return Optional.empty();
        }

        validateToken(token, user.get().getToken(), user.get().getLastLogin());

        return Optional.ofNullable(userToUserResumeDtoConverter.convert(user.get().clone()));
    }

    private void validateToken(String token, String tokenUser, LocalDateTime time) {

        LocalDateTime validTime = LocalDateTime.now().minusMinutes(30);

        if(!token.equals(tokenUser) || validTime.isAfter(time)){
            throw new UnauthorizedException("Não autorizado");
        }
    }

    private void validateToken(String token) {
        if(Objects.isNull(token)){
            throw new UnauthorizedException("Não autorizado");
        }
    }
}
