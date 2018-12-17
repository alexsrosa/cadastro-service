package com.desafio.concrete.solutions.cadastroservice.domain.repository;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    boolean existsByEmail(String email);

    Optional<User> findOneByEmail(String email);
}
