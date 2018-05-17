package com.zisal.security.springbootjwtsecurity.endpoint;

import com.zisal.security.springbootjwtsecurity.dto.RequestInsertUserDTO;
import com.zisal.security.springbootjwtsecurity.dto.ResponseAllData;
import com.zisal.security.springbootjwtsecurity.dto.ResponseData;
import com.zisal.security.springbootjwtsecurity.dto.UserDTO;
import org.springframework.web.bind.annotation.*;


/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RequestMapping("/user")
public interface IUserEndPoint {

    @PostMapping("/insert")
    @ResponseBody
    ResponseData insert(RequestInsertUserDTO p_RequestInsertUserDTO);

    @PutMapping("/update")
    @ResponseBody
    ResponseData update(UserDTO p_UserDTO);

    @DeleteMapping("/delete")
    @ResponseBody
    ResponseData delete(Long p_Id);

    @GetMapping("/findall")
    @ResponseBody
    ResponseAllData findAll();
}
