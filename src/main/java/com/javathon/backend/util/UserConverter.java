package com.javathon.backend.util;

import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;

import java.util.HashMap;

public class UserConverter {

    public static User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLastLatitude(userDTO.getLastLatitude());
        user.setLastLongitude(userDTO.getLastLongitude());
        user.setUsername(userDTO.getUsername());
        user.setVkId(userDTO.getVkId());
        user.setVisible(userDTO.isVisible());
        user.setLastSeenDate(userDTO.getLastSeenDate());
        user.setFriend(new HashMap<>());
        return user;
    }
}
