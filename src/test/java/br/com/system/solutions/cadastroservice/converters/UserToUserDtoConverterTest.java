package br.com.system.solutions.cadastroservice.converters;

import br.com.system.solutions.cadastroservice.domain.entity.PhoneEntity;
import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserDtoConverter;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
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

        UserEntity user = new UserEntity();
        user.setName("João da Silva");
        user.setEmail("joao@silva.org");
        user.setPassword("hunter2");

        PhoneEntity phone = new PhoneEntity();
        phone.setNumber("987654321");
        phone.setDdd("21");

        Set<PhoneEntity> phoneList = new HashSet<>();
        phoneList.add(phone);
        user.setPhones(phoneList);

        when(userToUserDtoConverter.convert(user)).thenReturn(modelMapper.map(user, UserDto.class));

        UserDto userDto = userToUserDtoConverter.convert(user);

        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getPhones().size(), user.getPhones().size());

        user.getPhones().forEach(p -> userDto.getPhones().forEach(d -> {
                    assertEquals(p.getNumber(), d.getNumber());
                    assertEquals(p.getDdd(), d.getDdd());
                }
        ));
    }
}
