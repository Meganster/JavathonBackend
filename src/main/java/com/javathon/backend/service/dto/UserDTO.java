package com.javathon.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.javathon.backend.model.User;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@JsonDeserialize(builder = UserDTO.Builder.class)
public class UserDTO {

    @JsonProperty("id")
    final long id;
    @JsonProperty("vkId")
    final long vk_id;
    @JsonProperty("username")
    final String username;
    @JsonProperty("lastLatitude")
    final double lastLatitude;
    @JsonProperty("lastLongitude")
    final double lastLongitude;
    @JsonProperty("isVisible")
    final boolean isVisible;
    @JsonProperty("lastSeenDate")
    final LocalDateTime localDateTime;
    @JsonProperty("friends")
    final List<UserDTO> userDTOList;

    @JsonPOJOBuilder
    public static class Builder {
        private User user;

        private long id;
        @JsonProperty("vkId")
        private long vkId;
        @JsonProperty("username")
        private String username;
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

        public Builder(@JsonProperty("id") long id) {
            this.id = id;
        }

        public Builder(User user) {
            this.id = user.getId();
            this.user = user;
        }

        public Builder setDefaultConfig() {
            this.vkId = this.user.getVkId();
            this.username = this.user.getUsername();
            this.lastLatitude = this.user.getLastLatitude();
            this.lastLongitude = this.user.getLastLongitude();
            this.isVisible = this.user.isVisible();
            this.lastSeenDate = this.user.getLastSeenDate();
            return this;
        }

        public Builder addFriends() {
            this.userDTOList.add(new UserDTO.Builder(this.user).setDefaultConfig().build());
            return this;
        }

        public UserDTO build () {
            return new UserDTO(this);
        }

    }

    private UserDTO (Builder builder) {
        this.id = builder.id;
        this.vk_id = builder.vkId;
        this.username = builder.username;
        this.lastLatitude = builder.lastLatitude;
        this.lastLongitude = builder.lastLongitude;
        this.isVisible = builder.isVisible;
        this.localDateTime = builder.lastSeenDate;
        this.userDTOList = getUserDTOList();
    }

}
