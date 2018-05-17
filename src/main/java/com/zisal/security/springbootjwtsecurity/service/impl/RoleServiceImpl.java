package com.zisal.security.springbootjwtsecurity.service.impl;

import com.zisal.security.springbootjwtsecurity.dao.IRoleDAO;
import com.zisal.security.springbootjwtsecurity.entity.Role;
import com.zisal.security.springbootjwtsecurity.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 4/28/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDAO roleDAO;

    @Override
    public List<Role> findAllRoles() {
        return roleDAO.findAll();
    }
}
