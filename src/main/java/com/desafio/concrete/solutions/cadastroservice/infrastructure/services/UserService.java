package com.desafio.concrete.solutions.cadastroservice.infrastructure.services;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.helpers.CryptPasswordHelper;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.helpers.SecurityHelper;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.database.repositories.UserJpaRepository;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.EmailFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe de serviço do usuário.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Service
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * Método que efetua a criação de um novo usuário.
     *
     * @param user objeto do tipo {@link UserEntity}
     * @return retorna um {@link Optional} de {@link UserEntity}
     */
    public Optional<UserEntity> create(UserEntity user) {

        validateEmailFound(user.getEmail());

        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setPassword(CryptPasswordHelper.cryptPasswordEncoder(user.getPassword()));
        user.setToken(SecurityHelper.generateTokenJwt(user));

        return Optional.ofNullable(userJpaRepository.save(user));
    }

    private void validateEmailFound(String email) {
        if (userJpaRepository.existsByEmail(email)) {
            throw new EmailFoundException();
        }
    }

    public Optional<UserEntity> findOne(UUID id) {
        return userJpaRepository.findById(id);
    }

    public Optional<UserEntity> findOneByEmail(String email) {
        return userJpaRepository.findOneByEmail(email);
    }

    public UserEntity login(UserEntity user) {

        user.setToken(SecurityHelper.generateTokenJwt(user));
        user.setLastLogin(LocalDateTime.now());
        return userJpaRepository.save(user);
    }
}
