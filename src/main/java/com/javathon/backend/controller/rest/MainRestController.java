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

    @GetMapping("/update_position")
    public ResponseEntity updatePosition(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updatePosition(userDTO));
    }
}
