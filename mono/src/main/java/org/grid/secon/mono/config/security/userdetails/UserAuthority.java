package org.grid.secon.mono.config.security.userdetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Table(name = "user_authority")
public class UserAuthority {
    @Id
    @GeneratedValue
    private UUID id;

    @Version
    private Integer version;

    @NotEmpty
    @Column(nullable = false)
    private String authority;

    /**
     * for hibernate
     */
    public UserAuthority() {

    }

    public UserAuthority(@NotEmpty String authority) {
        this.authority = authority;
    }

    public UUID getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
