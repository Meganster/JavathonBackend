package com.javathon.backend.util;

import com.javathon.backend.service.dto.AuthDTO;
import com.javathon.backend.service.dto.MessageDTO;
import com.javathon.backend.service.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UniversalResponse {
    private Boolean success;
    private AuthDTO authDTO;
    private UserDTO friend;
    private Map<Long, List<MessageDTO>> messages = new HashMap<>();
    private Map<Long, UserDTO> friends = new HashMap<>();

    public static UniversalResponse OK(){
        UniversalResponse universalResponse = new UniversalResponse();
        universalResponse.setSuccess(true);
        return universalResponse;
    }

    public static UniversalResponse BAD(){
        UniversalResponse universalResponse = new UniversalResponse();
        universalResponse.setSuccess(false);
        return universalResponse;
    }
}
