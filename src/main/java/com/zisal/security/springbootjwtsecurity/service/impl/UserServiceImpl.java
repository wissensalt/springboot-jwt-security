package com.zisal.security.springbootjwtsecurity.service.impl;

import com.zisal.security.springbootjwtsecurity.dao.IUserDAO;
import com.zisal.security.springbootjwtsecurity.entity.User;
import com.zisal.security.springbootjwtsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public void insert(User p_User) {
        userDAO.save(p_User);
    }

    @Override
    public void update(User p_User) {
        userDAO.save(p_User);
    }

    @Override
    public void delete(Long p_Id) {
        userDAO.delete(p_Id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> findByUserName(String p_UserName) {
        return userDAO.findByCode(p_UserName);
    }
}
