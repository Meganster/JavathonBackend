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
    @JsonProperty("imei")
    final long imei;
    @JsonProperty("vk_id")
    final long vk_id;
    @JsonProperty("username")
    final String username;
    @JsonProperty("last_latitude")
    final double last_latitude;
    @JsonProperty("last_longitude")
    final double last_longitude;
    @JsonProperty("isVisible")
    final boolean isVisible;
    @JsonProperty("last_seen_date")
    final LocalDateTime last_seen_date;
    @JsonProperty("friends")
    final List<UserDTO> userDTOList;

    @JsonPOJOBuilder
    public static class Builder {
        private User user;

        private long id;
        @JsonProperty("imei")
        private long imei;
        @JsonProperty("vk_id")
        private long vk_id;
        @JsonProperty("username")
        private String username;
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

        public Builder(@JsonProperty("id") long id) {
            this.id = id;
        }

        public Builder(User user) {
            this.id = user.getId();
            this.user = user;
        }

        public Builder setDefaultConfig() {
            this.imei = this.user.getImei();
            this.vk_id = this.user.getVk_id();
            this.username = this.user.getUsername();
            this.last_latitude = this.user.getLast_latitude();
            this.last_longitude = this.user.getLast_longitude();
            this.isVisible = this.user.isVisible();
            this.last_seen_date = this.user.getLast_seen_date();
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
        this.imei = builder.imei;
        this.vk_id = builder.vk_id;
        this.username = builder.username;
        this.last_latitude = builder.last_latitude;
        this.last_longitude = builder.last_longitude;
        this.isVisible = builder.isVisible;
        this.last_seen_date = builder.last_seen_date;
        this.userDTOList = getUserDTOList();
    }

}
