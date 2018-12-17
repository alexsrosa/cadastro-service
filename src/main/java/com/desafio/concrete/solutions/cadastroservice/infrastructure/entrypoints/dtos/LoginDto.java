package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank(message = "Campo email deve ser informado.")
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
