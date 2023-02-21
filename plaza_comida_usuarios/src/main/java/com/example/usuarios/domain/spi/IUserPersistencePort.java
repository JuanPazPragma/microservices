package com.example.usuarios.domain.spi;

import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserPersistencePort {
    AuthenticationResponseDto saveUser(UserModel userModel);
    Optional<UserEntity> findUserByEmail(String email);
}