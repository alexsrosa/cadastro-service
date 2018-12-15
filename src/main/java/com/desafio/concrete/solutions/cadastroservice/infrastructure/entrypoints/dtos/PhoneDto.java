package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class PhoneDto {

    private UUID id;
    private String number;
    private String ddd;

    @JsonIgnore
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
