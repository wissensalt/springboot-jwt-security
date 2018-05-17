package com.zisal.security.springbootjwtsecurity.config;

import com.zisal.security.springbootjwtsecurity.entity.CustomUserDetails;
import com.zisal.security.springbootjwtsecurity.entity.User;
import com.zisal.security.springbootjwtsecurity.service.IAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        User user = authenticationService.login(s);
        if (user != null) {
            if (user.getPassword() == null || user.getPassword().trim().length() <= 0) {
                user.setPassword("");
            }
            return new CustomUserDetails(user);
        }else {
            LOGGER.error("User is not found");
            throw new UsernameNotFoundException("User is not found");
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = authenticationService.login(authentication.getName());
        if (user != null) {
            if (user.getPassword() == null || user.getPassword().trim().length() <= 0) {
                user.setPassword("");
            }
            if (passwordEncoder.matches(String.valueOf(authentication.getCredentials()), user.getPassword())) {
                return super.authenticate(authentication);
            }
        }else {
            LOGGER.error("User is not found");
        }
        return null;
    }
}
