package com.example.auth_service.PrepMate_AI.usermanagement.db.dao;

import com.example.auth_service.PrepMate_AI.usermanagement.db.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository <Users, Long>
{

}
