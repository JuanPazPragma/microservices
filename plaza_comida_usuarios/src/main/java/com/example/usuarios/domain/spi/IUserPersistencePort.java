package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.UserModel;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel userModel);
}
