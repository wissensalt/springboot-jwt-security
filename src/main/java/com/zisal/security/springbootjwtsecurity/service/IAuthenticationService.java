package com.zisal.security.springbootjwtsecurity.service;

import com.zisal.security.springbootjwtsecurity.entity.User;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IAuthenticationService {

    User login(String p_UserName);
}
