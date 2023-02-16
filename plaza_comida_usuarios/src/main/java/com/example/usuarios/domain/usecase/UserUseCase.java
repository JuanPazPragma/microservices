package com.example.usuarios.domain.usecase;

import com.example.usuarios.domain.api.IUserServicePort;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public UserModel saveUser(UserModel userModel) {
        userPersistencePort.saveUser(userModel);
        return userModel;
    }
}
