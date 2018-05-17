package com.zisal.security.springbootjwtsecurity.endpoint.impl;

import com.zisal.security.springbootjwtsecurity.endpoint.IEchoEndPoint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
public class EchoEndPointImpl implements IEchoEndPoint {

    @Override
    public String echoParam(@PathVariable("param") String p_Param) {
        System.out.println("PRINCIPAL " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Collection<?extends GrantedAuthority> granted = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role;
        for(int i=0;i<granted.size();i++){
            role = granted.toArray()[i] + "";
            System.out.println("role verified" + i + " is -> " + role);
        }
        return "Welcome ".concat(p_Param);
    }
}
