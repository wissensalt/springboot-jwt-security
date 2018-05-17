package com.zisal.security.springbootjwtsecurity.exception;

import javax.naming.AuthenticationException;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class JwtTokenMalformedException extends AuthenticationException {

    public JwtTokenMalformedException(String explanation) {
        super(explanation);
    }
}
