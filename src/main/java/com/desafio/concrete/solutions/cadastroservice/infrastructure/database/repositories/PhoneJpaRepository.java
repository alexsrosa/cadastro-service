package com.desafio.concrete.solutions.cadastroservice.infrastructure.database.repositories;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.PhoneEntity;
import com.desafio.concrete.solutions.cadastroservice.domain.repository.PhoneRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneJpaRepository extends JpaRepository<PhoneEntity, Integer>, PhoneRepository {

}
