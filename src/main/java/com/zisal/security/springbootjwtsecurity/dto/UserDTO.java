package com.zisal.security.springbootjwtsecurity.dto;

import lombok.Data;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
public class UserDTO extends RequestInsertUserDTO {
    /**
     *
     *
     */
    private static final long serialVersionUID = -6391177175963652730L;

    public UserDTO(String code, String name, Long id) {
        super(code, name);
        this.id = id;
    }

    private Long id;
}
