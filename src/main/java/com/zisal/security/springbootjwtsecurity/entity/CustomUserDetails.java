package com.zisal.security.springbootjwtsecurity.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created on 5/2/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class CustomUserDetails extends User implements UserDetails {
    /**
     *
     *
     */
    private static final long serialVersionUID = 3727198706022925008L;

    public CustomUserDetails(User p_User) {
        super(p_User.getCode(), p_User.getName(), p_User.getPassword(), p_User.getEnabled(), p_User.getAccountNonExpired(), p_User.getAccountNonLocked(), p_User.getCredentialsNonExpired(), p_User.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        getRoles().stream().forEach(System.out::println);
        return getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.getEnabled();
    }
}
