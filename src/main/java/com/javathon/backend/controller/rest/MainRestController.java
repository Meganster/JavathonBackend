package com.javathon.backend.controller.rest;

import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.service.interf.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/")
public class MainRestController {
    private final UserService userService;

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
}
