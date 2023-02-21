package com.example.usuarios.domain.usecase;

import com.example.usuarios.domain.api.IUserServicePort;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.domain.spi.IUserPersistencePort;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;

import java.util.Optional;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public UserModel saveUser(UserModel userModel) {
        return userPersistencePort.saveUser(userModel);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userPersistencePort.existsUserByEmail(email);
    }

    @Override
    public Boolean existsUserByName(String name) {
        return userPersistencePort.existsUserByName(name);
    }

    @Override
    public Optional<UserEntity> getByUserName(String name) {
        return userPersistencePort.getByUser(name);
    }
}
