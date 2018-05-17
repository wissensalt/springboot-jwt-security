package com.zisal.security.springbootjwtsecurity.config;

import com.zisal.security.springbootjwtsecurity.security.AuthenticationTokenFilter;
import com.zisal.security.springbootjwtsecurity.security.CustomAccessDeniedHandler;
import com.zisal.security.springbootjwtsecurity.security.JwtAuthenticationSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Created on 4/27/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilter() {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        try {
            authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        } catch (Exception e) {
            LOGGER.error("Error Set Authentication Manager : {}", e.toString());
        }
        authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                // All urls must be authenticated (filter for token always fires (/**)
//                .authorizeRequests().anyRequest().authenticated()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/echo/**").hasRole("USER")
                .antMatchers("/user/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                        // Call our errorHandler if authentication/authorisation fails
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(customAccessDeniedHandler)
                .and()
                        // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Custom JWT based security filter
        http
                .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
