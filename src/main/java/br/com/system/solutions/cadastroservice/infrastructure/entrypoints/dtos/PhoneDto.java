package br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos;

import br.com.system.solutions.cadastroservice.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;
import javax.validation.constraints.NotBlank;

/**
 * Classe de transferência de dados de telefone.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
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
