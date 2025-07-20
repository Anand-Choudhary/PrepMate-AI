package com.example.auth_service.PrepMate_AI.usermanagement.utility;

import com.example.auth_service.PrepMate_AI.usermanagement.db.dao.UserDao;
import com.example.auth_service.PrepMate_AI.usermanagement.db.models.Users;

import java.util.UUID;

public class UniqueToken
{
    public String createUniqueToken()
    {
        return String.valueOf(UUID.randomUUID());
    }
}
