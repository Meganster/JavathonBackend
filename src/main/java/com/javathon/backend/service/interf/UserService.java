package com.javathon.backend.service.interf;

import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.util.UniversalResponse;

public interface UserService {
    void saveUser(User user);
    User getUserByImei(long imei);
    void getFriendsPosition(UniversalResponse universalResponse);
    String getToken();
    String getShortToken();
    
}
