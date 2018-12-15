package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    private final ModelMapper modelMapper;

    public UserToUserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto convert(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
