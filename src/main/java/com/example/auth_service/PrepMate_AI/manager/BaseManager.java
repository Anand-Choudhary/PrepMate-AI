package com.example.auth_service.PrepMate_AI.manager;


import com.example.auth_service.PrepMate_AI.controllers.ResponseDTO;

public interface BaseManager <Resource, ID>{

    ResponseDTO<Resource> findById(ID id);

    ResponseDTO findAll();

    ResponseDTO<Resource> create(Resource resource);

    ResponseDTO<Resource> update(Resource resource);

}

