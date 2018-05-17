package com.zisal.security.springbootjwtsecurity.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
@EqualsAndHashCode
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 50, unique = true)
    private String code;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "password", length = 150)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "link_user_role",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )}
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "hashed")
    private Boolean hashed;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    public User() {
    }

    public User(String code, String name, String password, Boolean enabled, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Set<Role> roles) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.roles = roles;
    }
}
