package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotBlank;

/**
 * Classe de transferência de dados do usuário.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
public class UserDto {

    private UUID id;

    @NotBlank(message = "Campo name deve ser informado.")
    private String name;

    @NotBlank(message = "Campo email deve ser informado.")
    private String email;

    @NotBlank(message = "Campo password deve ser informado.")
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private UUID token;
    private Set<PhoneDto> phones;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Set<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneDto> phones) {
        this.phones = phones;
    }
}
