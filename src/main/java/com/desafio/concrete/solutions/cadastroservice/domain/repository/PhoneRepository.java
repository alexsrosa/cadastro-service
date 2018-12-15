package com.desafio.concrete.solutions.cadastroservice.domain.repository;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.Phone;

import java.util.List;
import java.util.UUID;

public interface PhoneRepository {

    List<Phone> findByUserId(UUID id);
}
