package com.zisal.security.springbootjwtsecurity.security;

import com.zisal.security.springbootjwtsecurity.exception.JwtTokenMalformedException;
import com.zisal.security.springbootjwtsecurity.service.CustomUserDetailsService;
import com.zisal.security.springbootjwtsecurity.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${jwt.header}")
    private String jwtHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String token = httpServletRequest.getHeader(jwtHeader);
        if (token != null) {
            String userName = jwtTokenUtil.getUserNameFromToken(token);
            UserAuthenticationContext userAuthenticationContext = UserAuthenticationContext.getInstance();
            if (userName != null && userAuthenticationContext.getDefaultAuthentication() == null) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.getPassword(), userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new CustomAuthenticationDetails(httpServletRequest));
                    userAuthenticationContext.setAuthentication(authenticationToken);
                } else {
                    try {
                        throw new JwtTokenMalformedException("Token is not valid");
                    } catch (JwtTokenMalformedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            LOGGER.warn("Accessing Resource Without Token");
        }

        chain.doFilter(req, res);
    }
}
