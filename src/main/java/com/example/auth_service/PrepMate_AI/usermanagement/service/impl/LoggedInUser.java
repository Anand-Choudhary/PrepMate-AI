package com.example.auth_service.PrepMate_AI.usermanagement.service.impl;

import com.example.auth_service.PrepMate_AI.usermanagement.db.dao.UserDao;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class LoggedInUser implements UserDetailsService
{
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        try{
            User user = userDao.findByEmail(username);

            if(user == null)
            {
                throw new UsernameNotFoundException("User not found with email: " + username);
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    new ArrayList<>()
            );
        }
        catch(UsernameNotFoundException u)
        {
            log.error("User not found in loadUserByUsername",u);
            throw u;
        }
        catch(Exception e)
        {
            log.error("Exception in loadUserByUsername",e);
            throw e;
        }

    }
}
