package com.javathon.backend.service.impl;

import com.javathon.backend.dao.UserDao;
import com.javathon.backend.model.db.User;
import com.javathon.backend.security.Interceptors.MainInterceptor;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.service.interf.UserService;
import com.javathon.backend.util.RandomString;
import com.javathon.backend.util.UniversalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final MainInterceptor mainInterceptor;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, MainInterceptor mainInterceptor) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.mainInterceptor = mainInterceptor;
    }
    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.findByVkId(id);
    }

    @Override
    public String getToken() {
        return passwordEncoder.encode(UUID.randomUUID().toString());
    }

    @Override
    public UniversalResponse updatePosition(UserDTO userDTO) {
        UniversalResponse universalResponse = new UniversalResponse();
        //Update user
        User user = userDao.findByVkId(userDTO.getVkId());
        user.setLastLatitude(userDTO.getLastLatitude());
        user.setLastLongitude(userDTO.getLastLongitude());
        user.setLastSeenDate(LocalDateTime.now());
        //Send friends position

        user.getFriend().forEach((vkId, friend) -> {
            universalResponse.getFriends().put(vkId, new UserDTO.Builder(friend).setDefault_config().build());
        });

        universalResponse.setSuccess(true);
        return universalResponse;
    }

    @Override
    public UniversalResponse getFriendPosition(long id) {
        UniversalResponse universalResponse = new UniversalResponse();
        User user = userDao.findUserByToken(mainInterceptor.getToken());
        if(user == null) {
            universalResponse.setSuccess(false);
            return universalResponse;
        }
        UserDTO userDTO = new UserDTO.Builder(user).setDefault_config().build();
        universalResponse.setFriend(userDTO);
        universalResponse.setSuccess(true);
        return universalResponse;
    }

    @Override
    public String getShortToken() {
        return RandomString.getShortTokenString();
    }
}
