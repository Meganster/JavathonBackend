package com.javathon.backend.service;

import com.javathon.backend.dao.UserDao;
import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserDao userDao;
    private final UserConverter userConverter;

    @Autowired
    public UserService (UserDao userDao, UserConverter userConverter) {
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
