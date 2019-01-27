package com.javathon.backend.util;

import com.javathon.backend.model.db.User;
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
//    public static UserDTO convertUserToUserDTO(User user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setImei(user.getImei());
//        userDTO.setLastLatitude(user.getLastLatitude());
//        userDTO.setLastLongitude(user.getLastLongitude());
//        userDTO.setLastSeenDate(user.getLastSeenDate());
//        userDTO.setUsername(user.getUsername());
//        userDTO.setVkId(user.getVkId());
//        userDTO.setVisible(user.isVisible());
//        return userDTO;
//    }
}
