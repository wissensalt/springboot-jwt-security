package com.zisal.security.springbootjwtsecurity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 5/16/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
public class JwtUserDTO implements Serializable{

    private String userName;
    private String role;

}
