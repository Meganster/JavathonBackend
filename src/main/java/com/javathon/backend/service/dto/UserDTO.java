package com.javathon.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.javathon.backend.model.User;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private long id;
    private long vkId;
    private String username;
    private String recoveryCode;
    private long imei;
    private double lastLatitude;
    private double lastLongitude;
    private boolean isVisible;
    private LocalDateTime localDateTime;
    private List<UserDTO> userDTOList;


//    public UserDTO() {}
}
