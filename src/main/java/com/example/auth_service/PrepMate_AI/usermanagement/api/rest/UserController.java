package com.example.auth_service.PrepMate_AI.usermanagement.api.rest;

import com.example.auth_service.PrepMate_AI.controllers.ResponseDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.api.manager.UserManager;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.UserDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping(value="/register")
    public ResponseDTO createUser(@Valid @RequestBody UserDTO userDTO)
    {
        try{
            return userManager.create(userDTO);
        }
        catch (Exception e)
        {
            log.error("Error in UserController");
            throw e;
        }

    }

}
