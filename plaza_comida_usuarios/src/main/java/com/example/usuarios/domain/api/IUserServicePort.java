package com.example.usuarios.domain.api;

import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserServicePort {
    UserModel saveUser(UserModel userModel);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByName(String name);
    Optional<UserEntity> getByUserName(String name);
}
