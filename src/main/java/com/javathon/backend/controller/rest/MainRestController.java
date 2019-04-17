package com.javathon.backend.controller.rest;

import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.service.interf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class MainRestController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(MainRestController.class);

    public MainRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update_position")
    public ResponseEntity updatePosition(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updatePosition(userDTO));
    }

    @GetMapping("/friend_position/{vkId}")
    public ResponseEntity getFriendPosition(@PathVariable long vkId) {
        return ResponseEntity.ok(userService.getFriendPosition(vkId));
    }

    @GetMapping("/friend_messages")
    public ResponseEntity getFriendMessages() {
        return ResponseEntity.ok(userService.getFriendsMessages());
    }
}
