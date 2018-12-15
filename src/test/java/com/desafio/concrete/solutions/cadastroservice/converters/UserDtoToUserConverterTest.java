package com.desafio.concrete.solutions.cadastroservice.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDtoToUserConverterTest {

    @InjectMocks
    private UserDtoToUserConverter userDtoToUserConverter;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void init(){
        modelMapper = new ModelMapper();
    }

    @Test
    public void userDtoToUserConverterSuccessTest(){

        UserDto userDto = new UserDto();
        userDto.setName("João da Silva");
        userDto.setEmail("joao@silva.org");
        userDto.setPassword("hunter2");

        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("987654321");
        phoneDto.setDdd("21");

        List<PhoneDto> phoneList = new ArrayList<>();
        phoneList.add(phoneDto);
        userDto.setPhones(phoneList);

        when(userDtoToUserConverter.convert(userDto)).thenReturn(modelMapper.map(userDto, User.class));

        User user = userDtoToUserConverter.convert(userDto);

        assertEquals(user.getName(), user.getName());
        assertEquals(user.getEmail(), user.getEmail());
        assertEquals(user.getPassword(), user.getPassword());
        assertEquals(user.getPhones().size(), user.getPhones().size());
        assertEquals(user.getPhones().get(0).getNumber(), user.getPhones().get(0).getNumber());
        assertEquals(user.getPhones().get(0).getDdd(), user.getPhones().get(0).getDdd());
    }
}
