package com.javathon.backend.util;

import com.javathon.backend.service.dto.AuthDTO;
import com.javathon.backend.service.dto.MessageDTO;
import com.javathon.backend.service.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UniversalResponse {
    private Boolean success;
    private AuthDTO authDTO;
    private UserDTO friend;
    private Map<Long, List<MessageDTO>> messages = new HashMap<>();
    private List<UserDTO> friends = new ArrayList<>();

    public static UniversalResponse OK() {
        UniversalResponse universalResponse = new UniversalResponse();
        universalResponse.setSuccess(true);
        return universalResponse;
    }

    public static UniversalResponse BAD() {
        UniversalResponse universalResponse = new UniversalResponse();
        universalResponse.setSuccess(false);
        return universalResponse;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AuthDTO getAuthDTO() {
        return authDTO;
    }

    public void setAuthDTO(AuthDTO authDTO) {
        this.authDTO = authDTO;
    }

    public UserDTO getFriend() {
        return friend;
    }

    public void setFriend(UserDTO friend) {
        this.friend = friend;
    }

    public Map<Long, List<MessageDTO>> getMessages() {
        return messages;
    }

    public void setMessages(Map<Long, List<MessageDTO>> messages) {
        this.messages = messages;
    }

    public List<UserDTO> getFriends() {
        return friends;
    }

    public void setFriends(List<UserDTO> friends) {
        this.friends = friends;
    }
}
