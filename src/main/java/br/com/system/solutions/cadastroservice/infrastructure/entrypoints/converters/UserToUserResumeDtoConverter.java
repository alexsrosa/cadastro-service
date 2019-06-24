package br.com.system.solutions.cadastroservice.infrastructure.entrypoints.converters;

import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Classe de conversão da classe {@link UserEntity} para {@link UserResumeDto}.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Component
public class UserToUserResumeDtoConverter implements Converter<UserEntity, UserResumeDto> {

    private final ModelMapper modelMapper;

    public UserToUserResumeDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResumeDto convert(UserEntity user) {
        return modelMapper.map(user, UserResumeDto.class);
    }
}
