package com.example.auth_service.PrepMate_AI.usermanagement.api.mappers;


import com.example.auth_service.PrepMate_AI.mappers.BaseMapper;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.UserDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserMapper implements BaseMapper<User, UserDTO>
{

    @Override
    public User mapToModel(UserDTO userDTO)
    {
        try {
            log.info("Inside mapToModel in UserMapper");
            User user = new User();
            if(userDTO!=null)
            {

                if(userDTO.getId()!=null)
                {
                    user.setId(userDTO.getId());
                }
                user.setName(userDTO.getName());
                user.setEmail(userDTO.getEmail());
                user.setPassword(userDTO.getPassword());
                user.setDateOfBirth(userDTO.getDateOfBirth());
                user.setPhoneNumber(userDTO.getPhoneNumber());
                user.setExperience(userDTO.getExperience());
            }
            return user;
        }
        catch (Exception e)
        {
            log.error("Exception in mapToModel in UserMapper while mapping UserDto to User error msg :{}",e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public UserDTO mapToResource(User users)
    {
        try
        {
            UserDTO userDTO = new UserDTO();
            if(users!=null)
            {
                userDTO.setId(users.getId());
                userDTO.setName(users.getName());
                userDTO.setEmail(users.getEmail());
                userDTO.setPassword(users.getPassword());
                userDTO.setDateOfBirth(users.getDateOfBirth());
                userDTO.setPhoneNumber(users.getPhoneNumber());
                userDTO.setExperience(users.getExperience());
            }
            return userDTO;
        }
        catch(Exception e)
        {
            log.error("Exception in mapToResources in UserMapper while mapping UserDto error msg :{}",e.getMessage(),e);
            throw e;
        }
    }
}
