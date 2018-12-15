package com.desafio.concrete.solutions.cadastroservice.usecases.service;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.database.repositories.PhoneJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneService {

    private final PhoneJpaRepository phoneRepository;

    public PhoneService(PhoneJpaRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Optional<User> create(Optional<User> user) {
        if(user.isPresent()){
            user.get().getPhones().forEach( phone -> {
                phone.setUser(user.get());
                phoneRepository.save(phone);
            });
        }
        return user;
    }
}
