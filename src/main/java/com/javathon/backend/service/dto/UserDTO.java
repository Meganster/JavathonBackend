package com.javathon.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.javathon.backend.model.User;
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
    private final String username;
    private final String recovery_code;
    private final long imei;
    private final double last_latitude;
    private final double last_longitude;
    private final boolean isVisible;
    private final LocalDateTime last_seen_date;
    private final List<UserDTO> userDTOList;


    @JsonPOJOBuilder
    public static class Builder {
        private User user;

        private long id;
        @JsonProperty("vk_id")
        private long vkId;
        @JsonProperty("username")
        private String username;
        @JsonProperty("recovery_code")
        private String recovery_code;
        @JsonProperty("imei")
        private long imei;
        @JsonProperty("last_latitude")
        private double last_latitude;
        @JsonProperty("last_longitude")
        private double last_longitude;
        @JsonProperty("isVisible")
        private boolean isVisible;
        @JsonProperty("last_seen_date")
        private LocalDateTime last_seen_date;
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
            this.username = this.user.getUsername();
            this.last_latitude = this.user.getLastLatitude();
            this.last_longitude = this.user.getLastLongitude();
            this.isVisible = this.user.isVisible();
            this.last_seen_date = this.user.getLastSeenDate();
            return this;
        }

        public Builder addFriends() {
            this.user.getFriend().forEach((aLong, friend) ->  {
                        this.userDTOList.add(new UserDTO.Builder(friend).setDefault_config().build());
                    }
            );
            return this;
        }

        public UserDTO build () {
            return new UserDTO(this);
        }

    }

    private UserDTO (Builder builder) {
        this.id = builder.id;
        this.vkId = builder.vkId;
        this.username = builder.username;
        this.last_latitude= builder.last_latitude;
        this.last_longitude = builder.last_longitude;
        this.isVisible = builder.isVisible;
        this.last_seen_date = builder.last_seen_date;
        this.userDTOList = builder.userDTOList;
        this.imei = builder.imei;
        this.recovery_code = builder.recovery_code;
    }
}
