package com.javathon.backend.util;

import com.javathon.backend.service.dto.AuthDTO;
import com.javathon.backend.service.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UniversalResponse {
    private Boolean success;
    private AuthDTO authDTO;
    private UserDTO friend;
    private Map<Long, UserDTO> friends = new HashMap<>();
}
