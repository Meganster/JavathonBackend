package com.javathon.backend.util;

import com.javathon.backend.model.db.events.impl.EventMessage;
import com.javathon.backend.service.dto.MessageDTO;

import java.time.LocalDateTime;

public class MessageConverter {
    public static EventMessage convertMessageDTOToEventMessage(MessageDTO messageDTO) {
        EventMessage eventMessage = new EventMessage();
        eventMessage.setCreateTime(LocalDateTime.now());
        eventMessage.setDescription(messageDTO.getDescription());
        eventMessage.setLatitude(messageDTO.getLatitude());
        eventMessage.setLongitude(messageDTO.getLongitude());
        return eventMessage;
    }
}
