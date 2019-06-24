package br.com.system.solutions.cadastroservice.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Classe de entidate do usuário.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@Entity
@Table(name = "USER")
public class UserEntity implements Cloneable{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Campo name não pode ser vazio!")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Campo email não pode ser vazio!")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "Campo password não pode ser vazio!")
    private String password;

    @NotNull(message = "Campo created não pode ser nulo!")
    private LocalDateTime created;

    @NotNull(message = "Campo modified não pode ser nulo!")
    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    private String token;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<PhoneEntity> phones = new HashSet<>();

    public UserEntity() {
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneEntity> phones) {
        this.phones = phones;
    }

    public UserEntity clone() throws CloneNotSupportedException {
        return (UserEntity) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getCreated(), user.getCreated()) &&
                Objects.equals(getModified(), user.getModified()) &&
                Objects.equals(getLastLogin(), user.getLastLogin()) &&
                Objects.equals(getToken(), user.getToken()) &&
                Objects.equals(getPhones(), user.getPhones());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPassword(), getCreated(),
                getModified(), getLastLogin(), getToken(), getPhones());
    }
}
