package com.javathon.backend.service.interf;

import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);
    User getUserByImei(long imei);
}
