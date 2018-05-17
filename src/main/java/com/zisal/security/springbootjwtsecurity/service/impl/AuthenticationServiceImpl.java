package com.zisal.security.springbootjwtsecurity.service.impl;

import com.zisal.security.springbootjwtsecurity.dao.IUserDAO;
import com.zisal.security.springbootjwtsecurity.entity.CustomUserDetails;
import com.zisal.security.springbootjwtsecurity.entity.User;
import com.zisal.security.springbootjwtsecurity.service.IAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private IUserDAO userDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public User login(String p_UserName) {
        User user = userDAO.findUserByCodeOrUserProfile_phoneNumberOrUserProfile_email(p_UserName, p_UserName, p_UserName);
        if(user != null) {
            LOGGER.warn("User Found");
            return new CustomUserDetails(user);
        }else {
            LOGGER.warn("User Not Found");
            return null;
        }
    }
}
