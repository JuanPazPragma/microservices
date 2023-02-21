package com.example.usuarios.application.handler;

import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto);
    Optional<UserEntity> getByUserName(String userName);
    Boolean existsByUserName(String name);
    Boolean existsByEmail(String email);

}
