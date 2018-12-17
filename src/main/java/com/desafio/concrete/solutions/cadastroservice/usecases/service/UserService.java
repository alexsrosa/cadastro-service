package com.desafio.concrete.solutions.cadastroservice.usecases.service;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.database.repositories.UserJpaRepository;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.EmailFoundException;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UnauthorizedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserJpaRepository userJpaRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<User> create(User user){

        if(userJpaRepository.existsByEmail(user.getEmail())){
            throw new EmailFoundException();
        }

        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setToken(UUID.randomUUID());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return Optional.ofNullable(userJpaRepository.save(user));
    }

    public Optional<User> findOne(UUID id) {
        return userJpaRepository.findById(id);
    }

    public Optional<User> findOneByEmail(String email){
        return userJpaRepository.findOneByEmail(email);
    }

    public boolean isPasswordInvalid(String requestPassword, String password) {
        return !bCryptPasswordEncoder.matches(requestPassword, password);
    }

    public User login(User user) {
        user.setToken(UUID.randomUUID());
        user.setLastLogin(LocalDateTime.now());
        return userJpaRepository.save(user);
    }
}
