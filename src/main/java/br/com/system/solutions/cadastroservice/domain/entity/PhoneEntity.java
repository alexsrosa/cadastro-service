package br.com.system.solutions.cadastroservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe de entidate do telefone.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Entity
@Table(name = "PHONE")
public class PhoneEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Campo number não pode ser vazio!")
    @Size(max = 9)
    private String number;

    @NotBlank(message = "Campo ddd não pode ser vazio!")
    @Size(max = 3)
    private String ddd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneEntity)) return false;
        PhoneEntity phone = (PhoneEntity) o;
        return Objects.equals(getId(), phone.getId()) &&
                Objects.equals(getNumber(), phone.getNumber()) &&
                Objects.equals(getDdd(), phone.getDdd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getDdd());
    }
}
