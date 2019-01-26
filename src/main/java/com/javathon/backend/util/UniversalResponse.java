package com.javathon.backend.util;

import com.javathon.backend.service.dto.UserDTO;

import java.util.HashMap;
import java.util.Map;

public class UniversalResponse {
    Map<Long, UserDTO> friends = new HashMap<>();
}
