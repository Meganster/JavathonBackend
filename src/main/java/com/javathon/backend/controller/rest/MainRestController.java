package com.javathon.backend.controller.rest;

import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.util.UniversalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/")
public class MainRestController {
    @GetMapping("/friends_position")
    public ResponseEntity updatePosition(UserDTO userDTO) {
        UniversalResponse universalResponse = new UniversalResponse();

        return ResponseEntity.ok("ok");
    }
}
