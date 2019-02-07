package com.javathon.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDTO {
    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("recoveryCode")
    private String recoveryCode;
}
