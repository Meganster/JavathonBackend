package com.javathon.backend.util;

import com.javathon.backend.service.dto.UserDTO;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UniversalResponse {
    Map<Long, UserDTO> friends = new HashMap<>();
}
