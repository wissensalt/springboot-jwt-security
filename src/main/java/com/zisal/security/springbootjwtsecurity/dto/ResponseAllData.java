package com.zisal.security.springbootjwtsecurity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
public class ResponseAllData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 4019941543569316340L;
    private List<UserDTO> content;
    private ResponseData responseData;
}
