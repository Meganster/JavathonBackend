package com.javathon.backend.util;

import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
        user.setLastSeenDate(userDTO.getLocalDateTime());
        user.setFriend(new HashMap<>());
        return user;
    }
    public static UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setImei(user.getImei());
        userDTO.setLastLatitude(user.getLastLatitude());
        userDTO.setLastLongitude(user.getLastLongitude());
        userDTO.setLocalDateTime(user.getLastSeenDate());
        userDTO.setUsername(user.getUsername());
        userDTO.setVkId(user.getVkId());
        userDTO.setVisible(user.isVisible());
        return userDTO;
    }
}
