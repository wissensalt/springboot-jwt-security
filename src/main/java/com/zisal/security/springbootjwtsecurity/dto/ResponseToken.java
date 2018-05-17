package com.zisal.security.springbootjwtsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created on 5/14/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseToken implements Serializable {
    private String token;
    private ResponseData responseData;
}
