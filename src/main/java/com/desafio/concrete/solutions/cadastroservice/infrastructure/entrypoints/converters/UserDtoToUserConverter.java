package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    private final ModelMapper modelMapper;

    public UserDtoToUserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User convert(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
