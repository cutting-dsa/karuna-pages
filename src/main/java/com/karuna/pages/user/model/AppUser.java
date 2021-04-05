package com.karuna.pages.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.karuna.pages.core.exceptions.BadRequestException;
import com.karuna.pages.core.exceptions.UnsupportedTypeException;
import com.karuna.pages.role.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name", nullable = false, unique = true)
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Integer enabled;

    // bi-directional many-to-many association to Role
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Collection<Role> roles;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AppUser appUser = ((AppUser) obj);
        return Objects.equals(username, appUser.getUsername()) && Objects.equals(password, appUser.getPassword());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [name=").append(username).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }

    public void editUser(String username, String firstName, String lastName, Integer enabled, String password, Collection<Role> roles){
        if(username != null) this.setUsername(username);

        if(firstName != null) this.setFirstName(firstName);

        if(lastName != null) this.setLastName(lastName);

        if (enabled != null ) {
            if( enabled < 0 || enabled > 1) throw new UnsupportedTypeException("Value enabled can only be 0 or 1");

            this.setEnabled(enabled);
        }

        if(password != null) this.setPassword(password);

        if(roles != null) {

            if(roles.isEmpty())  throw new BadRequestException("User must have at least one role");
            this.setRoles(roles);
        }


    }

    public void disable(){
        this.enabled = 0;
    }
}
