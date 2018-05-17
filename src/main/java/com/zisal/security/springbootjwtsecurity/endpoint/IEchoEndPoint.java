package com.zisal.security.springbootjwtsecurity.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RequestMapping("/echo")
public interface IEchoEndPoint {

    @GetMapping("/{param}")
    String echoParam(@PathVariable("param") String p_Param);
}
