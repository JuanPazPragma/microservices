package com.example.usuarios.domain.api;

import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserServicePort {
    UserModel saveUser(UserModel userModel);

    Optional<UserEntity> findUserByEmail(String email);

    UserModel findUserByEmailModel(String email);

    UserModel getById(Long userId);
}
