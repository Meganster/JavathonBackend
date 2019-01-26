package com.javathon.backend.util;

import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserConverter {

    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setImei(userDTO.getImei());
        user.setLastLatitude(userDTO.getLastLatitude());
        user.setLastLongitude(userDTO.getLastLongitude());
        user.setUsername(userDTO.getUsername());
        user.setVkId(userDTO.getVk_id());
        user.setVisible(userDTO.isVisible());
        user.setLastSeenDate(userDTO.getLocalDateTime());
        user.setFriend(new HashMap<>());
        return user;
    }
}
