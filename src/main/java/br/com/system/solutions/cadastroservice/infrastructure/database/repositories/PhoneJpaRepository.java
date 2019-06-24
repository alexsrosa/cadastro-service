package br.com.system.solutions.cadastroservice.infrastructure.database.repositories;

import br.com.system.solutions.cadastroservice.domain.entity.PhoneEntity;
import br.com.system.solutions.cadastroservice.domain.repository.PhoneRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório da entidade {@link PhoneEntity}.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Repository
public interface PhoneJpaRepository extends JpaRepository<PhoneEntity, Integer>, PhoneRepository {

}
