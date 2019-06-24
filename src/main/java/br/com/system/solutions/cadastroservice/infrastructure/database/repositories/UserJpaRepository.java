package br.com.system.solutions.cadastroservice.infrastructure.database.repositories;

import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import br.com.system.solutions.cadastroservice.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Interface de repositório da entidade {@link UserEntity}.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID>, UserRepository {

}
