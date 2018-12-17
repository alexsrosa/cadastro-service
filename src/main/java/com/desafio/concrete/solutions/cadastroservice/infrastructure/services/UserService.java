package com.desafio.concrete.solutions.cadastroservice.infrastructure.services;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.database.repositories.UserJpaRepository;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.EmailFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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

    public Optional<UserEntity> create(UserEntity user) {

        if (userJpaRepository.existsByEmail(user.getEmail())) {
            throw new EmailFoundException();
        }

        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return Optional.ofNullable(userJpaRepository.save(user));
    }

    public Optional<UserEntity> findOne(UUID id) {
        return userJpaRepository.findById(id);
    }

    public Optional<UserEntity> findOneByEmail(String email) {
        return userJpaRepository.findOneByEmail(email);
    }

    public boolean isPasswordInvalid(String requestPassword, String password) {
        return !bCryptPasswordEncoder.matches(requestPassword, password);
    }

    public UserEntity login(UserEntity user) {

        String JWT = Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + 30000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();

        user.setToken(JWT);
        user.setLastLogin(LocalDateTime.now());
        return userJpaRepository.save(user);
    }

}
