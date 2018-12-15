package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.Phone;
import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.PhoneDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneConverter implements Converter<Phone, PhoneDto> {

    @Override
    public PhoneDto convert(Phone phone) {

        PhoneDto dto = new PhoneDto();
        dto.setId(phone.getId());
        dto.setDdd(phone.getDdd());
        dto.setNumber(phone.getNumber());
        return dto;
    }

    public List<PhoneDto> convertAll(List<Phone> phones) {
        List<PhoneDto> phoneDtos = new ArrayList<>();
        phones.forEach(p -> phoneDtos.add(this.convert(p)));
        return phoneDtos;
    }
}
