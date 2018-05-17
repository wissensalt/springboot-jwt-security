package com.zisal.security.springbootjwtsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -8192744440342273913L;
    private String code;
    private String message;
}
