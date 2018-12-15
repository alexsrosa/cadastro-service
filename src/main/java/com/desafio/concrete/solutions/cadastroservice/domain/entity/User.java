package com.desafio.concrete.solutions.cadastroservice.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class User implements Cloneable{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull @NotEmpty
    @Size(max = 100)
    private String name;

    @NotNull @NotEmpty
    @Size(max = 100)
    private String email;

    @NotNull @NotEmpty
    @Size(max = 14)
    private String password;

    @NotNull
    private LocalDateTime created;

    @NotNull
    private LocalDateTime modified;

    private LocalDateTime lastLogin;

    @NotNull
    private UUID token;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Phone> phones = new HashSet<>();

    public User() {
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

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
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
