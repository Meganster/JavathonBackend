package com.javathon.backend.service.dto;

import lombok.Data;

@Data
public class AuthDTO {
    private String token;
    private String recoveryCode;
}
