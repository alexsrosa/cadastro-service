package com.desafio.concrete.solutions.cadastroservice.infrastructure.services;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.database.repositories.PhoneJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneService {

    private final PhoneJpaRepository phoneRepository;

    public PhoneService(PhoneJpaRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Optional<UserEntity> create(Optional<UserEntity> user) {
        if(user.isPresent()){
            user.get().getPhones().forEach( phone -> {
                phone.setUser(user.get());
                phoneRepository.save(phone);
            });
        }
        return user;
    }
}
