package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel userModel);

    Boolean existsUserByEmail(String email);

    Boolean existsUserByName(String name);

    Optional<UserEntity> getByUser(String name);
}
