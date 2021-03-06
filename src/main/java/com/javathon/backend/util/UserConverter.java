package com.javathon.backend.util;

import com.javathon.backend.model.db.User;
import com.javathon.backend.service.dto.UserDTO;

import java.util.HashMap;

public class UserConverter {

    public static User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setLastLatitude(userDTO.getLastLatitude());
        user.setLastLongitude(userDTO.getLastLongitude());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setVkId(userDTO.getVkId());
        user.setVisible(userDTO.isVisible());
        user.setLastSeenDate(userDTO.getLastSeenDate());
        user.setFriend(new HashMap<>());
        return user;
    }
}
