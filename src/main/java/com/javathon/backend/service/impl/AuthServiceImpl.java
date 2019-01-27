package com.javathon.backend.service.impl;

import com.javathon.backend.dao.UserDao;
import com.javathon.backend.model.db.User;
import com.javathon.backend.service.dto.AuthDTO;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.service.interf.AuthService;
import com.javathon.backend.service.interf.UserService;
import com.javathon.backend.util.UniversalResponse;
import com.javathon.backend.util.UserConverter;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final UserService userService;

    public AuthServiceImpl(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @Override
    public UniversalResponse create(UserDTO userDTO) {
        UniversalResponse universalResponse = new UniversalResponse();
        String token = userService.getToken();
        String shortToken = userService.getShortToken();
        User user = UserConverter.convertUserDTOToUser(userDTO);
        user.setToken(token);
        user.setRecovery_code(shortToken);
        user.setImei(userDTO.getImei());
        user.setVkId(userDTO.getVkId());
        user.setUsername(userDTO.getUsername());

        for (UserDTO friend : userDTO.getUserDTOList()) {
            User target;
            if((target = userDao.findByVkId(friend.getVkId())) == null) {
                target = new User();
                target.setVkId(friend.getVkId());
                target = userDao.save(target);
                user.getFriend().put(target.getVkId(), target);
            }
            user.getFriend().put(target.getVkId(), target);
        }

        userService.saveUser(user);
        AuthDTO authDTO = new AuthDTO(token, shortToken);
        universalResponse.setSuccess(true);
        universalResponse.setAuthDTO(authDTO);
        return universalResponse;
    }

    @Override
    public UniversalResponse recovery(User user, UserDTO userDTO) {
        UniversalResponse universalResponse = new UniversalResponse();
        user.setImei(userDTO.getImei());
        String token = userService.getToken();
        String shortToken = userService.getShortToken();
        user.setToken(token);
        user.setRecovery_code(shortToken);
        userService.saveUser(user);
        AuthDTO authDTO = new AuthDTO(token, shortToken);
        universalResponse.setSuccess(true);
        universalResponse.setAuthDTO(authDTO);
        return universalResponse;
    }
}
