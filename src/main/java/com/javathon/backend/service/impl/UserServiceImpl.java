package com.javathon.backend.service.impl;

import com.javathon.backend.dao.UserDao;
import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.service.interf.UserService;
import com.javathon.backend.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl (UserDao userDao, UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }
    public void saveUser(UserDTO userDTO) {
        User user = userConverter.convert(userDTO);
        userDao.save(user);
    }

    public User getUserByImei(long imei) {
        return userDao.findByImei(imei);
    }
}
