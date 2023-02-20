package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel userModel);
    Optional<UserEntity> findUserByEmail(String email);
}
