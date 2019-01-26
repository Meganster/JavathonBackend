package com.javathon.backend.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/")
public class MainRestController {
    @GetMapping("/friends_position")
    public ResponseEntity getFriendPosition() {
        return ResponseEntity.ok("ok");
    }
}
