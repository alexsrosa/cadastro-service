package br.com.system.solutions.cadastroservice.infrastructure.entrypoints.converters;

import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Classe de conversão da classe {@link UserDto} para {@link UserEntity}.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Component
public class UserDtoToUserConverter implements Converter<UserDto, UserEntity> {

    private final ModelMapper modelMapper;

    public UserDtoToUserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity convert(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
