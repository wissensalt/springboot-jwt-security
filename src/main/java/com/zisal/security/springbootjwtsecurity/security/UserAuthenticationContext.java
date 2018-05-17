package com.zisal.security.springbootjwtsecurity.security;

import com.zisal.security.springbootjwtsecurity.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@NoArgsConstructor
public class UserAuthenticationContext {

    private static UserAuthenticationContext instance = null;

    public static UserAuthenticationContext getInstance() {
        if (instance == null) {
            instance = new UserAuthenticationContext();
        }
        return instance;
    }

    public String getCurrentUserName() {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationFilter))
            return SecurityContextHolder.getContext().getAuthentication().getName();
        return null;
    }

    public Authentication getGrantedAuthentication() {
        if (!(getDefaultAuthentication() instanceof AnonymousAuthenticationFilter))
            return getDefaultAuthentication();
        return null;
    }

    public Authentication getDefaultAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public boolean hasRole(String p_Role) {
        User user = (User) getDefaultAuthentication().getPrincipal();
        return user.getRoles().contains(new SimpleGrantedAuthority(p_Role));
    }
}
