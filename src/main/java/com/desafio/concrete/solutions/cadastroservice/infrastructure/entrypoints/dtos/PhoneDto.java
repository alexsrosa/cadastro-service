package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;
import javax.validation.constraints.NotBlank;

public class PhoneDto {

    private UUID id;

    @NotBlank(message = "Campo number deve ser informado.")
    private String number;

    @NotBlank(message = "Campo ddd deve ser informado.")
    private String ddd;

    @JsonIgnore
    private UserEntity user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
