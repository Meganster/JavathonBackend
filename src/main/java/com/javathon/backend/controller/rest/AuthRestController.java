package com.javathon.backend.controller.rest;

import com.javathon.backend.model.User;
import com.javathon.backend.service.UserService;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.util.UserConverter;
import com.javathon.backend.util.validators.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.validation.Validator;

@RestController
@RequestMapping(path = "/api")
public class AuthRestController {

    private final UserService userService;
    private final AuthValidator authValidator;
    private final UserConverter userConverter;

    @Autowired
    public AuthRestController(UserService userService, AuthValidator authValidator, UserConverter userConverter) {
        this.userService = userService;
        this.authValidator = authValidator;
        this.userConverter = userConverter;
    }

    @PostMapping(path = "/auth")
    public ResponseEntity create(@Valid UserDTO userDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errors);
        }
        String token = userService.getToken();
        User user = userConverter.convert(userDTO);
        user.setToken(token);
        userService.saveUser(user);
        return ResponseEntity.ok(token);
    }

    @InitBinder
    protected void initBinder(WebDataBinder bind) {
        bind.setValidator(authValidator);
    }
}
