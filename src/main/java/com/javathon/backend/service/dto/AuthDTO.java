package com.javathon.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDTO {
    @JsonProperty("token")
    private String token;
    @JsonProperty("recoveryCode")
    private String recoveryCode;
}
