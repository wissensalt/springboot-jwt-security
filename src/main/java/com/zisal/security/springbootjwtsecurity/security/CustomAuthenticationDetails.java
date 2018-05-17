package com.zisal.security.springbootjwtsecurity.security;

import com.zisal.security.springbootjwtsecurity.dto.AuthenticationDetailsDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomAuthenticationDetails extends WebAuthenticationDetails implements Serializable {

    private AuthenticationDetailsDTO authenticationDetailsDTO;

    public CustomAuthenticationDetails(HttpServletRequest request) {
        super(request);
        authenticationDetailsDTO = new AuthenticationDetailsDTO();
        authenticationDetailsDTO.setPlatform(request.getHeader("User-Agent"));
        authenticationDetailsDTO.setIpAddress(request.getHeader("X-FORWARDED-FOR"));
        if (authenticationDetailsDTO.getIpAddress() == null) {
            authenticationDetailsDTO.setIpAddress(request.getRemoteAddr());
        }
    }
}
