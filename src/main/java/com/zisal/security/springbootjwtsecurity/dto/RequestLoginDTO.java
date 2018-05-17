package com.zisal.security.springbootjwtsecurity.dto;

import lombok.Data;

import java.io.Serializable;import java.lang.String;

/**
 * Created on 5/14/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
public class RequestLoginDTO implements Serializable {

    private String userName;
    private String password;
}
