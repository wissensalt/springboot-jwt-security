package com.zisal.security.springbootjwtsecurity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 4/28/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
public class RoleDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 4262245510835236517L;

    private String code;
    private String name;
}
