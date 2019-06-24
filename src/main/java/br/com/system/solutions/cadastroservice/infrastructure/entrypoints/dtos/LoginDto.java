package br.com.system.solutions.cadastroservice.infrastructure.entrypoints.dtos;

import javax.validation.constraints.NotBlank;

/**
 * Classe de transferência de dados do login.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
public class LoginDto {

    @NotBlank(message = "Campo email deve ser informado.")
    private String email;
    
    @NotBlank(message = "Campo password deve ser informado.")
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
