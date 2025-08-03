package com.example.auth_service.PrepMate_AI.usermanagement.api.rest;

import com.example.auth_service.PrepMate_AI.controllers.ResponseDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.api.manager.UserManager;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.LogInDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.UserDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import com.example.auth_service.PrepMate_AI.usermanagement.service.impl.LoggedInUser;
import com.example.auth_service.PrepMate_AI.usermanagement.utility.JWTUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoggedInUser loggedInUser;



    @PostMapping(value="/register")
    public ResponseDTO<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO)
    {
        try
        {
            log.info("Inside createUser in UserController");
            return userManager.create(userDTO);
        }
        catch (Exception e)
        {
            log.error("Error while creating a new user in UserController : {}, stackTrace : {}", e.getMessage(), e.getStackTrace());
            return new ResponseDTO<>("400", e.getMessage(), false);
        }
    }

    @PostMapping(value = "/login")
    public ResponseDTO<LogInDTO> logInUser(@Valid @RequestBody LogInDTO logInDTO)
    {
        try {
            log.info("Inside logInUser in UserController");
            return userManager.loggingUser(logInDTO);
        }
        catch (Exception e)
        {
            log.error("Error logging in a user in UserController : {}, stackTrace : {}", e.getMessage(), e.getStackTrace());
            return new ResponseDTO<>("400", e.getMessage(), false);
        }

    }

}
