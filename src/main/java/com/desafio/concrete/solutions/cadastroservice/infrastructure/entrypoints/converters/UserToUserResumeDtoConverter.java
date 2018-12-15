package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResumeDtoConverter implements Converter<User, UserResumeDto> {

    private final ModelMapper modelMapper;

    public UserToUserResumeDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResumeDto convert(User user) {
        return modelMapper.map(user, UserResumeDto.class);
    }
}
