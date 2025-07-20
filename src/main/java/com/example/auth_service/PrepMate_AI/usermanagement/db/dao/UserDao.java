package com.example.auth_service.PrepMate_AI.usermanagement.db.dao;

import com.example.auth_service.PrepMate_AI.usermanagement.db.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository <User, Long>
{
    User findByEmail(String email);

}
