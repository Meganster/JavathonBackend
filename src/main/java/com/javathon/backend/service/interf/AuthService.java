package com.javathon.backend.service.interf;

import com.javathon.backend.model.db.User;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.util.UniversalResponse;

public interface AuthService {
    UniversalResponse create(UserDTO userDTO);

    UniversalResponse recovery(User user, UserDTO userDTO);
}
