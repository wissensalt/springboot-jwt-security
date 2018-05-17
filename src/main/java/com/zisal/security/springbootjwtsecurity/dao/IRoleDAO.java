package com.zisal.security.springbootjwtsecurity.dao;

import com.zisal.security.springbootjwtsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 4/28/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IRoleDAO extends JpaRepository<Role, Long> {
}
