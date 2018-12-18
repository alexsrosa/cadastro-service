package com.desafio.concrete.solutions.cadastroservice.domain.repository;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;

import java.util.Optional;

/**
 * Interface para inclusão de novos comportamentos de interação com o repositório.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
public interface UserRepository {

    boolean existsByEmail(String email);

    Optional<UserEntity> findOneByEmail(String email);
}
