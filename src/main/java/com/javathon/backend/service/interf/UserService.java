package com.javathon.backend.service.interf;

import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.util.UniversalResponse;

public interface UserService {
    void saveUser(User user);
    User getUserById(long id);
    String getToken();
    String getShortToken();
    UniversalResponse updatePosition(UserDTO userDTO);
}
