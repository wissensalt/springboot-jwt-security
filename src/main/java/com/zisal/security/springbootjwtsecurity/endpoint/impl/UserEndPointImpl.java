package com.zisal.security.springbootjwtsecurity.endpoint.impl;

import com.zisal.security.springbootjwtsecurity.converter.UserConverter;
import com.zisal.security.springbootjwtsecurity.dto.RequestInsertUserDTO;
import com.zisal.security.springbootjwtsecurity.dto.ResponseAllData;
import com.zisal.security.springbootjwtsecurity.dto.ResponseData;
import com.zisal.security.springbootjwtsecurity.dto.UserDTO;
import com.zisal.security.springbootjwtsecurity.endpoint.IUserEndPoint;
import com.zisal.security.springbootjwtsecurity.entity.User;
import com.zisal.security.springbootjwtsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
public class UserEndPointImpl implements IUserEndPoint {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserConverter userConverter;

    @Override
    public ResponseData insert(RequestInsertUserDTO p_RequestInsertUserDTO) {
        userService.insert(userConverter.convert(p_RequestInsertUserDTO));
        return new ResponseData("200", "Ok");
    }

    @Override
    public ResponseData update(UserDTO p_UserDTO) {
        userService.update(userConverter.convert2(p_UserDTO));
        return new ResponseData("200", "Ok");
    }

    @Override
    public ResponseData delete(Long p_Id) {
        userService.delete(p_Id);
        return new ResponseData("200", "Ok");
    }

    @Override
    public ResponseAllData findAll() {
        ResponseAllData responseAllData = new ResponseAllData();
        List<User> userList = userService.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList){
            userDTOList.add(userConverter.invert(user));
        }
        responseAllData.setResponseData(new ResponseData("200", "Ok"));
        responseAllData.setContent(userDTOList);
        return responseAllData;
    }
}
