package com.javathon.backend.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.javathon.backend.model.db.events.impl.EventMessage;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonDeserialize(builder = MessageDTO.Builder.class)
public class MessageDTO {
    @JsonProperty("latitude")
    private final double latitude;
    @JsonProperty("longitude")
    private final double longitude;
    @JsonProperty("createTime")
    private final LocalDateTime createTime;
    @JsonProperty("createTime")
    private final String description;

    @JsonPOJOBuilder
    public static class Builder {
        private EventMessage eventMessage;
        @JsonProperty("latitude")
        private double lastLatitude;
        @JsonProperty("longitude")
        private double lastLongitude;
        @JsonProperty("createTime")
        private LocalDateTime createTime;
        @JsonProperty("createTime")
        private String description;

        public Builder() {
        }

        public Builder(EventMessage eventMessage) {
            this.eventMessage = eventMessage;
        }

        public Builder setDefault_config() {
            this.lastLongitude = this.eventMessage.getLongitude();
            this.createTime = this.eventMessage.getCreateTime();
            this.description = this.eventMessage.getDescription();
            this.lastLatitude = this.eventMessage.getLatitude();
            return this;
        }

        public MessageDTO build() {
            return new MessageDTO(this);
        }
    }

    private MessageDTO(Builder builder) {
        this.latitude = builder.lastLatitude;
        this.longitude = builder.lastLongitude;
        this.createTime = builder.createTime;
        this.description = builder.description;
    }
}
