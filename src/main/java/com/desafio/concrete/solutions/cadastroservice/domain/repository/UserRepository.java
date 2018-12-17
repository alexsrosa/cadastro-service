package com.desafio.concrete.solutions.cadastroservice.domain.repository;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    boolean existsByEmail(String email);

    Optional<UserEntity> findOneByEmail(String email);
}
