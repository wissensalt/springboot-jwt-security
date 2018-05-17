package com.zisal.security.springbootjwtsecurity.converter;

import com.zisal.security.springbootjwtsecurity.dto.RequestInsertUserDTO;
import com.zisal.security.springbootjwtsecurity.dto.UserDTO;
import com.zisal.security.springbootjwtsecurity.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Created on 4/23/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class UserConverter implements Converter<RequestInsertUserDTO, User> {

    @Override
    public User convert(RequestInsertUserDTO requestInsertUserDTO) {
        User user = new User();
        user.setCode(requestInsertUserDTO.getCode());
        user.setName(requestInsertUserDTO.getName());
        return user;
    }

    public UserDTO invert(User p_User) {
        return new UserDTO(p_User.getCode(), p_User.getName(), p_User.getId());
    }

    public User convert2(UserDTO p_UserDTO) {
        User user = new User();
        user.setId(p_UserDTO.getId());
        user.setCode(p_UserDTO.getCode());
        user.setName(p_UserDTO.getName());
        return user;
    }
}
