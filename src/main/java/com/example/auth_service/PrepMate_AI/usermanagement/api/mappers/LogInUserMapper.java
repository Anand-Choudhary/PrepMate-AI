package com.example.auth_service.PrepMate_AI.usermanagement.api.mappers;

import com.example.auth_service.PrepMate_AI.mappers.BaseMapper;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.LogInDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogInUserMapper implements BaseMapper <User,LogInDTO>
{
    @Override
    public User mapToModel(LogInDTO logInDTO)
    {
        try {
            log.info("Inside mapToModel in UserMapper");
            User user = new User();
            if(logInDTO!=null)
            {
                user.setEmail(logInDTO.getEmail());
                user.setPassword(logInDTO.getPassword());
            }
            return user;
        }
        catch (Exception e)
        {
            log.error("Exception in mapToModel in LogInUserMapper while mapping LogInDto to User error msg :{}",e.getMessage(),e);
            throw e;
        }
    }


    @Override
    public LogInDTO mapToResource(User user) {
        try
        {
            LogInDTO loggedInDTO=new LogInDTO();
            loggedInDTO.setEmail(user.getEmail());
            loggedInDTO.setToken(user.getToken());
            loggedInDTO.setLoginStatus(user.getLoginStatus());
            return loggedInDTO;
        }
        catch(Exception e)
        {
            log.error("Exception in mapToResources in LogInUserMapper while mapping User to LogInDTO Dto error msg :{}",e.getMessage(),e);
            throw e;
        }
    }
}
