package com.example.auth_service.PrepMate_AI.usermanagement.service.impl;

import com.example.auth_service.PrepMate_AI.usermanagement.db.dao.UserDao;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.Users;
import com.example.auth_service.PrepMate_AI.usermanagement.service.UserService;
import com.example.auth_service.PrepMate_AI.usermanagement.utility.UniqueToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {


    private PasswordEncoder passwordEncoder;
    @Autowired
    private UniqueToken uniqueToken;
    @Autowired
    private UserDao userDao;
    @Override
    public Optional<Users> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Users> findAll() {
        return null;
    }

    @Override
    public Users create(Users users)
    {
        //TODO -> Activate user profile logic is pending.
        try{
            users.setActivationToken(uniqueToken.createUniqueToken());
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            Users newUser = userDao.save(users);
            return newUser;
        }
        catch (Exception e)
        {
            log.error("Error in registering a new user");
            throw e;
        }
    }

    @Override
    public Users update(Users users) {
        return null;
    }
}
