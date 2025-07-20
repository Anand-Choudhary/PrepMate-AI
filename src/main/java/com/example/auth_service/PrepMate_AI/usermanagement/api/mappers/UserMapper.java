package com.example.auth_service.PrepMate_AI.usermanagement.api.mappers;


import com.example.auth_service.PrepMate_AI.mappers.BaseMapper;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.UserDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserMapper implements BaseMapper<Users, UserDTO>
{

    @Override
    public Users mapToModel(UserDTO userDTO)
    {
        try {
            if(userDTO!=null)
            {
                Users user = new Users();
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

                return user;
            }
            return null;
        }
        catch (Exception e)
        {
            log.error("Exception in UserMapper in mapToModel while mapping UserDto error msg :{}, error:{}",e.getMessage(),e.getStackTrace());
            throw e;
        }
    }

    @Override
    public UserDTO mapToResource(Users users)
    {
        try
        {
            if(users!=null)
            {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(users.getId());
                userDTO.setName(users.getName());
                userDTO.setEmail(users.getEmail());
                userDTO.setPassword(users.getPassword());
                userDTO.setDateOfBirth(users.getDateOfBirth());
                userDTO.setPhoneNumber(users.getPhoneNumber());
                userDTO.setExperience(users.getExperience());
                return userDTO;
            }
            return null;
        }
        catch(Exception e)
        {
            log.error("Exception in UserMapper in mapToResources while mapping UserDto error msg :{}, error:{}",e.getMessage(),e.getStackTrace());
            throw e;
        }
    }
}
