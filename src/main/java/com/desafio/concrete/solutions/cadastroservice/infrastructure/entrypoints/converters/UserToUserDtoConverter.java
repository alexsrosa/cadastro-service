package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<UserEntity, UserDto> {

    private final ModelMapper modelMapper;

    public UserToUserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto convert(UserEntity user) {
        return modelMapper.map(user, UserDto.class);
    }
}
