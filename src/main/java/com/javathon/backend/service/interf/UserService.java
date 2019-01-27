package com.javathon.backend.service.interf;

import com.javathon.backend.model.db.User;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.util.UniversalResponse;

public interface UserService {
    void saveUser(User user);
    User getUserById(long id);
    String getToken();
    String getShortToken();
    UniversalResponse updatePosition(UserDTO userDTO);
    UniversalResponse getFriendPosition(long id);
    UniversalResponse setVisible(boolean isVisible);
    UniversalResponse addFriend(UserDTO userDTO);
}
