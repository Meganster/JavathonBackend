package com.javathon.backend.controller.rest;

import com.javathon.backend.model.User;
import com.javathon.backend.service.dto.AuthDTO;
import com.javathon.backend.service.dto.UserDTO;

import com.javathon.backend.util.UserConverter;
import com.javathon.backend.service.interf.UserService;
import com.javathon.backend.util.validators.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class AuthRestController {

    private final UserService userService;
    private final AuthValidator authValidator;

    @Autowired
    public AuthRestController(UserService userService, AuthValidator authValidator) {
        this.userService = userService;
        this.authValidator = authValidator;
    }

    @PostMapping(path = "/auth")
    public ResponseEntity create(@Valid @RequestBody UserDTO userDTO, Errors errors) {
        System.out.println("auth");
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errors);
        }
        String token = userService.getToken();
        String shortToken = userService.getShortToken();
        User user = UserConverter.convertUserDTOToUser(userDTO);
        user.setToken(token);
        user.setRecovery_code(shortToken);
        user.setImei(userDTO.getImei());
        user.setVkId(userDTO.getVkId());
        user.setUsername(userDTO.getUsername());
        userService.saveUser(user);
        AuthDTO authDTO = new AuthDTO(token, shortToken);
        return ResponseEntity.ok(authDTO);
    }
    @PostMapping(path = "/recovery")
    public ResponseEntity recovery(@Valid @RequestBody UserDTO userDTO) {
        User existUser = userService.getUserById(userDTO.getVkId());
        if (!existUser.getRecovery_code().equals(userDTO.getRecovery_code())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("forbidden");
        }
        existUser.setImei(userDTO.getImei());
        String token = userService.getToken();
        String shortToken = userService.getShortToken();
        existUser.setToken(token);
        existUser.setRecovery_code(shortToken);
        userService.saveUser(existUser);
        AuthDTO authDTO = new AuthDTO(token, shortToken);
        return ResponseEntity.ok(authDTO);
    }

    @InitBinder
    protected void initBinder(WebDataBinder bind) {
        bind.setValidator(authValidator);
    }
}
