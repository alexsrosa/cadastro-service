package com.desafio.concrete.solutions.cadastroservice.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.Phone;
import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserDtoConverter;
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
public class UserToUserDtoConverterTest {

    @InjectMocks
    private UserToUserDtoConverter userToUserDtoConverter;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void init(){
        modelMapper = new ModelMapper();
    }

    @Test
    public void userToUserDtoConverterSuccessTest(){

        User user = new User();
        user.setName("João da Silva");
        user.setEmail("joao@silva.org");
        user.setPassword("hunter2");

        Phone phone = new Phone();
        phone.setNumber("987654321");
        phone.setDdd("21");

        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);
        user.setPhones(phoneList);

        when(userToUserDtoConverter.convert(user)).thenReturn(modelMapper.map(user, UserDto.class));

        UserDto userDto = userToUserDtoConverter.convert(user);

        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getPhones().size(), user.getPhones().size());
        assertEquals(userDto.getPhones().get(0).getNumber(), user.getPhones().get(0).getNumber());
        assertEquals(userDto.getPhones().get(0).getDdd(), user.getPhones().get(0).getDdd());
    }
}
