package com.example.auth_service.PrepMate_AI.usermanagement.api.manager;

import com.example.auth_service.PrepMate_AI.controllers.ResponseDTO;
import com.example.auth_service.PrepMate_AI.manager.BaseManager;
import com.example.auth_service.PrepMate_AI.usermanagement.api.mappers.LogInUserMapper;
import com.example.auth_service.PrepMate_AI.usermanagement.api.mappers.UserMapper;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.LogInDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.UserDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.service.UserService;
import com.example.auth_service.PrepMate_AI.usermanagement.service.impl.LoggedInUser;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserManager implements BaseManager<UserDTO,Long>
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoggedInUser loggedInUser;

    @Autowired
    private LogInUserMapper logInUserMapper;

    @Autowired
    private UserService userService;

    @Override
    public ResponseDTO<UserDTO> findById(Long aLong) {
        return null;
    }

//    UserDto dto = userMapper.mapToResource(userService.create(userMapper.mapToModel(userDto)));


    @Override
    public ResponseDTO findAll() {
        return null;
    }

    @Override
    public ResponseDTO<UserDTO> create(@Valid UserDTO userDTO)
    {
        try{
            UserDTO dto = userMapper.mapToResource(userService.create(userMapper.mapToModel(userDTO)));
            return new ResponseDTO<UserDTO>("200", "SUCCESS", true, dto);
        }
        catch (Exception e)
        {
            log.error("Error in UserManager creating a new user");
            throw e;
        }
    }

    public ResponseDTO<LogInDTO> loggingUser(LogInDTO logInDTO)
    {
        try{
            LogInDTO loggedInUserDto = logInUserMapper.mapToResource(loggedInUser.loggingUser(logInUserMapper.mapToModel(logInDTO)));
            return new ResponseDTO<LogInDTO>("200","SUCCESS",true,loggedInUserDto);
        }
        catch (Exception e)
        {
            log.error("Error in logging in a user");
            throw e;
        }
    }


    @Override
    public ResponseDTO<UserDTO> update(UserDTO userDTO) {
        return null;
    }

}
