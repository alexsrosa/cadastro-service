package br.com.system.solutions.cadastroservice.repositories;

import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import br.com.system.solutions.cadastroservice.domain.repository.UserRepository;
import br.com.system.solutions.cadastroservice.domain.entity.PhoneEntity;
import net.minidev.json.writer.BeansMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity = new UserEntity();

    @Before
    public void init() {
        LocalDateTime now = LocalDateTime.now();

        userEntity.setName("João da Silva");
        userEntity.setEmail("joao@silva.org");
        userEntity.setPassword("hunter2");
        userEntity.setCreated(now);
        userEntity.setModified(now);

        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setNumber("987654321");
        phoneEntity.setDdd("21");

        Set<PhoneEntity> phoneList = new HashSet<>();
        phoneList.add(phoneEntity);
        userEntity.setPhones(phoneList);
    }

    @Test
    public void whenSaveUser_thenReturnUserSuccess() {
        // given
        entityManager.persist(userEntity);
        entityManager.flush();

        // when
        Optional<UserEntity> userFound = userRepository.findOneByEmail(userEntity.getEmail());

        // then
        if (userFound.isPresent()) {
            assertEquals(userFound.get().getName(), userEntity.getName());
            assertEquals(userFound.get().getEmail(), userEntity.getEmail());
        } else {
            assertFalse(Boolean.TRUE);
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void whenSaveUser_thenReturnUserEmailFail() throws CloneNotSupportedException {

        UserEntity userCloneToFail = new UserEntity();
        BeanUtils.copyProperties(userEntity, userCloneToFail);
        userCloneToFail.setEmail(null);

        // given
        entityManager.persist(userCloneToFail);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void whenSaveUser_thenReturnUserPasswordFail() throws CloneNotSupportedException {

        UserEntity userCloneToFail = new UserEntity();
        BeanUtils.copyProperties(userEntity, userCloneToFail);
        userCloneToFail.setPassword(null);

        // given
        entityManager.persist(userCloneToFail);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void whenSaveUser_thenReturnUserNameFail() throws CloneNotSupportedException {

        UserEntity userCloneToFail = new UserEntity();
        BeanUtils.copyProperties(userEntity, userCloneToFail);
        userCloneToFail.setName(null);

        // given
        entityManager.persist(userCloneToFail);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void whenSaveUser_thenReturnUserCreatedFail() throws CloneNotSupportedException {

        UserEntity userCloneToFail = new UserEntity();
        BeanUtils.copyProperties(userEntity, userCloneToFail);
        userCloneToFail.setCreated(null);

        // given
        entityManager.persist(userCloneToFail);
        entityManager.flush();
    }

    @Test(expected = ConstraintViolationException.class)
    public void whenSaveUser_thenReturnUserModifiedFail() throws CloneNotSupportedException {

        UserEntity userCloneToFail = new UserEntity();
        BeanUtils.copyProperties(userEntity, userCloneToFail);
        userCloneToFail.setModified(null);

        // given
        entityManager.persist(userCloneToFail);
        entityManager.flush();
    }

    @Test
    public void whenFindByEmailUser_thenReturnUserSuccess() {
        // given
        entityManager.persist(userEntity);
        entityManager.flush();

        // when
        Optional<UserEntity> userFound = userRepository.findOneByEmail(userEntity.getEmail());

        // then
        if (userFound.isPresent()) {
            assertEquals(userFound.get().getName(), userEntity.getName());
            assertEquals(userFound.get().getEmail(), userEntity.getEmail());
        } else {
            assertFalse(Boolean.TRUE);
        }
    }

    @Test
    public void whenFindByEmailUser_thenReturnUserFail() {
        // given
        entityManager.persist(userEntity);
        entityManager.flush();

        // when
        Optional<UserEntity> userFound = userRepository.findOneByEmail(userEntity.getEmail().concat("-"));

        // then
        assertFalse(userFound.isPresent());
    }
}
