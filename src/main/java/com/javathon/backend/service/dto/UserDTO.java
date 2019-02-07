package com.javathon.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.javathon.backend.model.db.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonDeserialize(builder = UserDTO.Builder.class)
public class UserDTO {
    private final long id;
    private final long vkId;
    private final String firstName;
    private final String lastName;
    private final String recoveryCode;
    private final String imei;
    private final double lastLatitude;
    private final double lastLongitude;
    private final boolean isVisible;
//    private final boolean isVisibleMessage;
    private final LocalDateTime lastSeenDate;
    private final List<UserDTO> userDTOList;


    @JsonPOJOBuilder
    public static class Builder {
        private User user;
        private long id;
        @JsonProperty("vkId")
        private long vkId;
        @JsonProperty("firstName")
        private String firstName;
        @JsonProperty("lastName")
        private String lastName;
        @JsonProperty("recoveryCode")
        private String recoveryCode;
        @JsonProperty("imei")
        private String imei;
        @JsonProperty("lastLatitude")
        private double lastLatitude;
        @JsonProperty("lastLongitude")
        private double lastLongitude;
        @JsonProperty("isVisible")
        private boolean isVisible;
        @JsonProperty("lastSeenDate")
        private LocalDateTime lastSeenDate;
        @JsonProperty("friends")
        private List<UserDTO> userDTOList = new ArrayList<>();

        public Builder() {
        }

        public Builder(User user) {
            this.id = user.getId();
            this.user = user;
        }

        public Builder setDefault_config() {
            this.vkId = this.user.getVkId();
            this.firstName = this.user.getFirstName();
            this.lastName = this.user.getLastName();
            this.lastLatitude = this.user.getLastLatitude();
            this.lastLongitude = this.user.getLastLongitude();
            this.isVisible = this.user.isVisible();
            this.lastSeenDate = this.user.getLastSeenDate();
            return this;
        }

        public Builder addFriends() {
            this.user.getFriend().forEach((aLong, friend) -> {
                        this.userDTOList.add(new UserDTO.Builder(friend).setDefault_config().build());
                    }
            );
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }

    }

    private UserDTO(Builder builder) {
        this.id = builder.id;
        this.vkId = builder.vkId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.lastLatitude = builder.lastLatitude;
        this.lastLongitude = builder.lastLongitude;
        this.isVisible = builder.isVisible;
        this.lastSeenDate = builder.lastSeenDate;
        this.userDTOList = builder.userDTOList;
        this.imei = builder.imei;
        this.recoveryCode = builder.recoveryCode;
    }
}
