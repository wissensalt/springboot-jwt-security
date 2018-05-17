package com.zisal.security.springbootjwtsecurity.endpoint;

import com.zisal.security.springbootjwtsecurity.dto.RequestLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 5/14/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RequestMapping("/auth")
public interface IAuthEndPoint {

    @PostMapping("/login")
    ResponseEntity login(@RequestBody RequestLoginDTO p_RequestLoginDTO);
}
