package com.desafio.concrete.solutions.cadastroservice.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserDtoToUserConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.PhoneDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDtoToUserConverterTest {

    @InjectMocks
    private UserDtoToUserConverter userDtoToUserConverter;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void init() {
        modelMapper = new ModelMapper();
    }

    @Test
    public void userDtoToUserConverterSuccessTest() {

        UserDto userDto = new UserDto();
        userDto.setName("João da Silva");
        userDto.setEmail("joao@silva.org");
        userDto.setPassword("hunter2");

        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("987654321");
        phoneDto.setDdd("21");

        Set<PhoneDto> phoneList = new HashSet<>();
        phoneList.add(phoneDto);
        userDto.setPhones(phoneList);

        when(userDtoToUserConverter.convert(userDto)).thenReturn(modelMapper.map(userDto, UserEntity.class));

        UserEntity user = userDtoToUserConverter.convert(userDto);

        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getPhones().size(), userDto.getPhones().size());
        user.getPhones().forEach(p -> userDto.getPhones().forEach(d -> {
                    assertEquals(p.getNumber(), d.getNumber());
                    assertEquals(p.getDdd(), d.getDdd());
                }
        ));
    }
}
