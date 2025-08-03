package com.example.auth_service.PrepMate_AI.usermanagement.service.impl;

import com.example.auth_service.PrepMate_AI.usermanagement.db.dao.UserDao;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import com.example.auth_service.PrepMate_AI.usermanagement.utility.JWTUtil;
import com.example.auth_service.PrepMate_AI.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class LoggedInUser implements UserDetailsService
{
    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String email)
    {
        try{
            User user = userDao.findByEmail(email);

            if(user == null)
            {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getActivationToken(),
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


    public User loggingUser(User user)
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            UserDetails userDetails = loadUserByUsername(user.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            User logINUser = userDao.findByEmail(user.getEmail());
            logINUser.setLoginCount(logINUser.getLoginCount()+1);
            logINUser.setLoginStatus(Status.ACTIVE);
            logINUser.setToken(jwt);
            return userDao.save(logINUser);
        }
        catch(Exception e)
        {
            log.error("Error here");
            throw e;
        }
    }



}
