package com.example.usuarios.domain.usecase;

import com.example.usuarios.application.dto.response.AuthenticationResponseDto;
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
    public Optional<UserEntity> findUserByEmail(String email) {
        return userPersistencePort.findUserByEmail(email);
    }

    @Override
    public UserModel findUserByEmailModel(String email) {
        return userPersistencePort.findUserByEmailModel(email);
    }

    @Override
    public UserModel getById(Long userId) {
        return userPersistencePort.getById(userId);
    }
}
