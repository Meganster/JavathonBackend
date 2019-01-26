package com.javathon.backend.controller;

import com.javathon.backend.service.UserService;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.util.validators.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.validation.Validator;

@RestController
@RequestMapping(path = "/api")
public class MainController {

    private final UserService userService;
    private final AuthValidator authValidator;

    @Autowired
    public MainController(UserService userService, AuthValidator authValidator) {
        this.userService = userService;
        this.authValidator = authValidator;
    }

    @PostMapping(path = "/auth")
    public ResponseEntity create(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.ok(null);
    }

    @InitBinder
    protected void initBinder(WebDataBinder bind) {
        Validator validator = authValidator;
    }
}
