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
        user.setLast_latitude(userDTO.getLast_latitude());
        user.setLast_longitude(userDTO.getLast_longitude());
        user.setUsername(userDTO.getUsername());
        user.setVk_id(userDTO.getVk_id());
        user.setVisible(userDTO.isVisible());
        user.setLast_seen_date(userDTO.getLast_seen_date());
        user.setFriend(new HashMap<>());
        return user;
    }
}
