package com.javathon.backend.service.impl;

import com.javathon.backend.dao.UserDao;
import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.service.interf.UserService;
import com.javathon.backend.util.UniversalResponse;
import com.javathon.backend.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl (UserDao userDao, UserConverter userConverter, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }
    public void saveUser(User user) {
        userDao.save(user);
    }

    public User getUserByImei(long imei) {
        return userDao.findByImei(imei);
    }

    @Override
    public void getFriendsPosition(UniversalResponse universalResponse) {

    }

    @Override
    public String getToken() {
        return passwordEncoder.encode(UUID.randomUUID().toString());
    }
}
