package com.zisal.security.springbootjwtsecurity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
public class AuthenticationDetailsDTO implements Serializable {
    private String platform;
    private String ipAddress;
}
