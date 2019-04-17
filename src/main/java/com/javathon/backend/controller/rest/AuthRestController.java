package com.javathon.backend.controller.rest;

import com.javathon.backend.model.db.User;
import com.javathon.backend.service.dto.AuthDTO;
import com.javathon.backend.service.dto.UserDTO;

import com.javathon.backend.service.interf.AuthService;
import com.javathon.backend.util.Misc;
import com.javathon.backend.service.interf.UserService;
import com.javathon.backend.util.validators.AuthValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthRestController {

    private final UserService userService;
    private final AuthValidator authValidator;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthService authService;

    @Autowired
    public AuthRestController(UserService userService, AuthValidator authValidator, AuthService authService) {
        this.userService = userService;
        this.authValidator = authValidator;
        this.authService = authService;
    }

    @PostMapping(path = "/auth")
    public ResponseEntity create(@Valid @RequestBody UserDTO userDTO, Errors errors) {
        logger.info("auth");
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Misc.buildErrorResponse(errors));
        }
        return ResponseEntity.ok(authService.create(userDTO));
    }

    @PostMapping(path = "/recovery")
    public ResponseEntity recovery(@RequestBody UserDTO userDTO) {
        logger.info("recovery");
        User existUser = userService.getUserByVkId(userDTO.getVkId());
        if (!existUser.getRecovery_code().equals(userDTO.getRecoveryCode())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("forbidden");
        }
        return ResponseEntity.ok(authService.recovery(existUser, userDTO));
    }

    @InitBinder
    protected void initBinder(WebDataBinder bind) {
        bind.setValidator(authValidator);
    }
}
