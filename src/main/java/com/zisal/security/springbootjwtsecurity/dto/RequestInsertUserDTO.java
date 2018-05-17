package com.zisal.security.springbootjwtsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
@AllArgsConstructor
public class RequestInsertUserDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 3678782082188110089L;

    public RequestInsertUserDTO() {
    }

    private String code;
    private String name;
}
