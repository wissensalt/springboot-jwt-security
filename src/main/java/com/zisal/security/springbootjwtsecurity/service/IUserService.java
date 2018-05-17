package com.zisal.security.springbootjwtsecurity.service;


import com.zisal.security.springbootjwtsecurity.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IUserService {

    void insert(User p_User);

    void update(User p_User);

    void delete(Long p_Id);

    List<User> findAll();

    Optional<User> findByUserName(String p_UserName);
}
