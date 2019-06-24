package br.com.system.solutions.cadastroservice.infrastructure.entrypoints.converters;

import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Classe de conversão da classe {@link UserEntity} para {@link UserDto}.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
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
