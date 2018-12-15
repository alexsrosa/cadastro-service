package com.desafio.concrete.solutions.cadastroservice.usecases.service;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.database.repositories.UserJpaRepository;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.EmailFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public Optional<User> create(User user){

        if(userJpaRepository.existsByEmail(user.getEmail())){
            throw new EmailFoundException();
        }

        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setToken(UUID.randomUUID());
        return Optional.ofNullable(userJpaRepository.save(user));
    }

    public Optional<User> findOne(UUID id) {
        return userJpaRepository.findById(id);
    }

    public Optional<User> findOneByEmail(String email){
        return userJpaRepository.findOneByEmail(email);
    }

    public Optional<User> findOneByEmailAndPassword(String email, String password){
        return userJpaRepository.findOneByEmailAndPassword(email, password);
    }
}
