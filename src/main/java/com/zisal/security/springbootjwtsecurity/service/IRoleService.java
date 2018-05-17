package com.zisal.security.springbootjwtsecurity.service;


import com.zisal.security.springbootjwtsecurity.entity.Role;

import java.util.List;

/**
 * Created on 4/28/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IRoleService {

    List<Role> findAllRoles();
}
