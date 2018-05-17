package com.zisal.security.springbootjwtsecurity.endpoint.impl;

import com.zisal.security.springbootjwtsecurity.dto.RequestLoginDTO;
import com.zisal.security.springbootjwtsecurity.dto.ResponseData;
import com.zisal.security.springbootjwtsecurity.dto.ResponseToken;
import com.zisal.security.springbootjwtsecurity.endpoint.IAuthEndPoint;
import com.zisal.security.springbootjwtsecurity.service.CustomUserDetailsService;
import com.zisal.security.springbootjwtsecurity.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 5/14/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
public class AuthEndPointImpl implements IAuthEndPoint {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${jwt.header}")
    private String jwtHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity login(@RequestBody RequestLoginDTO p_RequestLoginDTO) {
        ResponseToken responseToken = new ResponseToken();
        if (p_RequestLoginDTO == null) {
            responseToken.setResponseData(new ResponseData("500", "Bad Credentials"));
        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(p_RequestLoginDTO.getUserName(), p_RequestLoginDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(p_RequestLoginDTO.getUserName());
            Device device = DeviceUtils.getCurrentDevice(httpServletRequest);
            String token = jwtTokenUtil.generateToken(userDetails, device);

            responseToken.setToken(token);
            responseToken.setResponseData(new ResponseData("200", "Successful Login"));
        }
        return ResponseEntity.ok(responseToken);
    }
}
