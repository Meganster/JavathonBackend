package com.javathon.backend.controller.rest;

import com.javathon.backend.service.dto.MessageDTO;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.service.interf.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/setVisible")
    public ResponseEntity updatePosition(@PathParam("visible") boolean isVisible) {
        return ResponseEntity.ok(userService.setVisible(isVisible));
    }

    @PostMapping("/addFriend")
    public ResponseEntity addFriend(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.addFriend(userDTO));
    }

    @PostMapping("/createMessage")
    public ResponseEntity createMessage(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity.ok("");
    }
}
