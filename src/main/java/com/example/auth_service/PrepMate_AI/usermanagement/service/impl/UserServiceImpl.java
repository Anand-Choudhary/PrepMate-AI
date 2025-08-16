package com.example.auth_service.PrepMate_AI.usermanagement.service.impl;

import com.example.auth_service.PrepMate_AI.Kafka.Producers.AccountVerifyProducer;
import com.example.auth_service.PrepMate_AI.usermanagement.api.resources.UserDTO;
import com.example.auth_service.PrepMate_AI.usermanagement.db.dao.UserDao;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import com.example.auth_service.PrepMate_AI.usermanagement.service.UserService;
import com.example.auth_service.PrepMate_AI.usermanagement.utility.UniqueToken;
import com.example.auth_service.PrepMate_AI.utility.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UniqueToken uniqueToken;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountVerifyProducer accountVerifyProducer;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public User create(User users)
    {

        try{
            log.info("Inside create in UserServiceImpl");
            users.setActivationToken(uniqueToken.createUniqueToken());
            //TODO -> Activate user profile logic is pending.
            users.setAccountStatus(Status.ACTIVE);
            String encodedPassword = passwordEncoder.encode(users.getPassword());
            users.setPassword(encodedPassword);
            User newUser = userDao.save(users);

            accountVerifyProducer.sendVerificationMessage(newUser.getId().toString(), newUser.getEmail(), newUser.getActivationToken())
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Kafka message send failed for userId={}", newUser.getId(), ex);
                            // optional: save a "pending verification" record to DB, or push to retry queue
                        } else {
                            log.info("Kafka message sent for activating profile: {}", result);
                        }
                    });
            return newUser;
        }
        catch (Exception e)
        {
            log.error("Error in registering a new user in create in UserServiceImpl : {}",e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public User update(User users) {
        return null;
    }
}
