package org.grid.secon.mono.config.security.userdetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Version
    private Integer version;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String login;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @NotEmpty
    @Column(nullable = false)
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<UserAuthority> authorities = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addAuthority(UserAuthority authority) {
        authorities.add(authority);
    }

    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }
}
