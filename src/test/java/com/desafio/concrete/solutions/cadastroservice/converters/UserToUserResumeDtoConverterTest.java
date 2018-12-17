package com.desafio.concrete.solutions.cadastroservice.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.PhoneEntity;
import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserResumeDtoConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserToUserResumeDtoConverterTest {

    @InjectMocks
    private UserToUserResumeDtoConverter userToUserResumeDtoConverter;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void init() {
        modelMapper = new ModelMapper();
    }

    @Test
    public void userToUserDtoConverterSuccessTest() {

        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setName("João da Silva");
        user.setEmail("joao@silva.org");
        user.setPassword("hunter2");

        PhoneEntity phone = new PhoneEntity();
        phone.setNumber("987654321");
        phone.setDdd("21");

        Set<PhoneEntity> phoneList = new HashSet<>();
        phoneList.add(phone);
        user.setPhones(phoneList);

        when(userToUserResumeDtoConverter.convert(user)).thenReturn(modelMapper.map(user, UserResumeDto.class));

        UserResumeDto userResumeDto = userToUserResumeDtoConverter.convert(user);

        assertEquals(userResumeDto.getId(), user.getId());
        assertEquals(userResumeDto.getToken(), user.getToken());
        assertEquals(userResumeDto.getCreated(), user.getCreated());
        assertEquals(userResumeDto.getLastLogin(), user.getLastLogin());
        assertEquals(userResumeDto.getModified(), user.getModified());
    }
}
