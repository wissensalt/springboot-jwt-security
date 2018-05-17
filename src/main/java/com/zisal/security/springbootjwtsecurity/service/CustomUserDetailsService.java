package com.zisal.security.springbootjwtsecurity.service;

import com.zisal.security.springbootjwtsecurity.entity.CustomUserDetails;
import com.zisal.security.springbootjwtsecurity.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created on 5/2/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IAuthenticationService authenticationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String p_UserName) throws UsernameNotFoundException {
        User user = authenticationService.login(p_UserName);
        if (user != null) {
            if (user.getPassword().trim().length() < 0 || user.getPassword() == null) {
                user.setPassword("");
            }
        }else {
            LOGGER.warn("USER Not Found");
        }
        return new CustomUserDetails(user);
    }
}
