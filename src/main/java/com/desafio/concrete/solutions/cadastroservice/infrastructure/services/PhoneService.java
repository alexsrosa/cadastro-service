package com.desafio.concrete.solutions.cadastroservice.infrastructure.services;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.database.repositories.PhoneJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe de serviço do telefones.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Service
public class PhoneService {

    private final PhoneJpaRepository phoneRepository;

    public PhoneService(PhoneJpaRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Optional<UserEntity> create(Optional<UserEntity> user) {
        user.ifPresent(userEntity -> userEntity.getPhones().forEach(phone -> {
            phone.setUser(userEntity);
            phoneRepository.save(phone);
        }));
        return user;
    }
}
